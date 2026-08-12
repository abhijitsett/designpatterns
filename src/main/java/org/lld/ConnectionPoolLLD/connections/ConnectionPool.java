package org.lld.ConnectionPoolLLD.connections;

import org.lld.ConnectionPoolLLD.enums.ConnectionState;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.*;

public class ConnectionPool {
    private final int maxPoolSize;

    /*
     * Connections currently available.
     */
    private final BlockingQueue<PooledConnection> available =
            new LinkedBlockingQueue<>();

    /*
     * Connections currently being used.
     */
    private final Set<PooledConnection> inUse =
            ConcurrentHashMap.newKeySet();

    /*
     * Threads waiting for a connection.
     *
     * LinkedBlockingQueue gives FIFO ordering.
     */
    private final BlockingQueue<AcquireRequest> waiting =
            new LinkedBlockingQueue<>();

    /*
     * Protects connection creation.
     */
    private final Object poolLock = new Object();

    private int totalConnections = 0;

    private volatile boolean shutdown = false;


    public ConnectionPool(int maxPoolSize) {

        if (maxPoolSize <= 0) {
            throw new IllegalArgumentException(
                    "maxPoolSize must be > 0"
            );
        }

        this.maxPoolSize = maxPoolSize;
    }


    // =====================================================
    // ACQUIRE
    // =====================================================

    public PooledConnection acquire(long timeoutMillis)
            throws InterruptedException, TimeoutException {

        if (shutdown) {
            throw new IllegalStateException(
                    "Pool is already shut down"
            );
        }

        /*
         * STEP 1:
         * Try to get an existing available connection.
         */
        PooledConnection connection =
                available.poll();

        if (connection != null) {

            if (connection.isValid()) {

                connection.setState(
                        ConnectionState.IN_USE
                );

                inUse.add(connection);

                return connection;
            }

            /*
             * Connection is invalid.
             */
            destroy(connection);
        }


        /*
         * STEP 2:
         * Can we create a new connection?
         *
         * Check + create must be atomic.
         */
        synchronized (poolLock) {

            if (shutdown) {
                throw new IllegalStateException(
                        "Pool is already shut down"
                );
            }

            if (totalConnections < maxPoolSize) {

                /*
                 * No factory.
                 *
                 * We directly create the connection.
                 */
                DBConnections dbConnection =
                        new DBConnections();

                dbConnection.open();

                connection =
                        new PooledConnection(dbConnection);

                totalConnections++;

                connection.setState(
                        ConnectionState.IN_USE
                );

                inUse.add(connection);

                return connection;
            }
        }


        /*
         * STEP 3:
         * Pool is full.
         *
         * Create a waiting request.
         */
        AcquireRequest request =
                new AcquireRequest();

        waiting.offer(request);


        /*
         * STEP 4:
         * Wait for release().
         */
        try {

            connection =
                    request.await(timeoutMillis);

            if (connection == null) {

                throw new TimeoutException(
                        "No connection available"
                );
            }

            connection.setState(
                    ConnectionState.IN_USE
            );

            inUse.add(connection);

            return connection;

        } catch (TimeoutException e) {

            /*
             * Remove request from queue.
             */
            request.cancel();

            waiting.remove(request);

            throw e;
        }
    }


    // =====================================================
    // RELEASE
    // =====================================================

    public void release(PooledConnection connection) {

        if (connection == null) {
            return;
        }

        /*
         * Make sure it was actually borrowed.
         */
        if (!inUse.remove(connection)) {

            throw new IllegalArgumentException(
                    "Connection is not currently in use"
            );
        }


        /*
         * Check if connection is still healthy.
         */
        if (!connection.isValid()) {

            destroy(connection);

            return;
        }


        /*
         * Give connection to the first
         * waiting request.
         */
        while (true) {

            AcquireRequest request =
                    waiting.poll();

            /*
             * Nobody waiting.
             */
            if (request == null) {

                connection.setState(
                        ConnectionState.AVAILABLE
                );

                available.offer(connection);

                return;
            }


            /*
             * Request timed out.
             */
            if (request.isCancelled()) {
                continue;
            }


            /*
             * Transfer connection to waiter.
             */
            request.assign(connection);

            return;
        }
    }


    // =====================================================
    // DESTROY
    // =====================================================

    private void destroy(PooledConnection connection) {

        connection.close();

        synchronized (poolLock) {

            totalConnections--;

            if (totalConnections < 0) {
                totalConnections = 0;
            }
        }
    }


    // =====================================================
    // SHUTDOWN
    // =====================================================

    public void shutdown() {

        synchronized (poolLock) {

            if (shutdown) {
                return;
            }

            shutdown = true;
        }


        /*
         * Cancel all waiting requests.
         */
        AcquireRequest request;

        while ((request = waiting.poll()) != null) {

            request.cancel();
        }


        /*
         * Close available connections.
         */
        PooledConnection connection;

        while ((connection = available.poll()) != null) {

            connection.close();
        }


        /*
         * Close in-use connections.
         */
        for (PooledConnection pooled : inUse) {

            pooled.close();
        }

        inUse.clear();


        synchronized (poolLock) {

            totalConnections = 0;
        }
    }


    // =====================================================
    // GETTERS
    // =====================================================

    public int getTotalConnections() {

        synchronized (poolLock) {
            return totalConnections;
        }
    }

    public int getAvailableConnections() {
        return available.size();
    }

    public int getInUseConnections() {
        return inUse.size();
    }

    public int getWaitingRequests() {
        return waiting.size();
    }
}
