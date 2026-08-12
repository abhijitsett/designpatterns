package org.lld.ConnectionPoolLLD;

import org.lld.ConnectionPoolLLD.connections.ConnectionPool;
import org.lld.ConnectionPoolLLD.connections.PooledConnection;

import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Pool can have maximum 2 connections.
         */
        ConnectionPool pool =
                new ConnectionPool(2);


        Runnable task = () -> {

            PooledConnection connection = null;

            try {

                System.out.println(
                        Thread.currentThread().getName()
                                + " requesting connection"
                );

                connection =
                        pool.acquire(5000);

                System.out.println(
                        Thread.currentThread().getName()
                                + " acquired connection"
                );

                /*
                 * Simulate DB operation.
                 */
                Thread.sleep(2000);

            } catch (TimeoutException e) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " timed out"
                );

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            } finally {

                if (connection != null) {

                    pool.release(connection);

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " released connection"
                    );
                }
            }
        };


        Thread t1 =
                new Thread(task, "Thread-1");

        Thread t2 =
                new Thread(task, "Thread-2");

        Thread t3 =
                new Thread(task, "Thread-3");

        Thread t4 =
                new Thread(task, "Thread-4");


        t1.start();
        t2.start();
        t3.start();
        t4.start();


        t1.join();
        t2.join();
        t3.join();
        t4.join();


        System.out.println(
                "Total = "
                        + pool.getTotalConnections()
        );

        System.out.println(
                "Available = "
                        + pool.getAvailableConnections()
        );

        System.out.println(
                "InUse = "
                        + pool.getInUseConnections()
        );

        System.out.println(
                "Waiting = "
                        + pool.getWaitingRequests()
        );


        pool.shutdown();
    }
}
