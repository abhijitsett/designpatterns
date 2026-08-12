package org.lld.ConnectionPoolLLD.connections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AcquireRequest {

    private final CountDownLatch latch =
            new CountDownLatch(1);

    private volatile PooledConnection connection;

    private volatile boolean cancelled;

    public void assign(PooledConnection connection) {

        if (cancelled) {
            return;
        }
        this.connection = connection;

        latch.countDown();
    }

    public PooledConnection await(long timeoutMillis)
            throws InterruptedException, TimeoutException {

        boolean completed =
                latch.await(timeoutMillis, TimeUnit.MILLISECONDS);

        if (!completed) {

            cancelled = true;

            throw new TimeoutException(
                    "Timed out waiting for connection"
            );
        }

        return connection;
    }
    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public PooledConnection getConnection() {
        return connection;
    }

}
