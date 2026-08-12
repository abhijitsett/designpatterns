package org.lld.ConnectionPoolLLD.connections;


import org.lld.ConnectionPoolLLD.interfaces.Connection;
import org.lld.ConnectionPoolLLD.enums.ConnectionState;

import java.time.Instant;

public class PooledConnection {
    private final Connection connection;

    private final Instant createdAt;

    private Instant lastUsedAt;

    private ConnectionState state;

    public PooledConnection(Connection connection) {

        this.connection = connection;
        this.createdAt = Instant.now();
        this.lastUsedAt = Instant.now();
        this.state = ConnectionState.AVAILABLE;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getId() {
        return connection.getId();
    }

    public synchronized ConnectionState getState() {
        return state;
    }

    public synchronized void setState(ConnectionState state) {
        this.state = state;
        this.lastUsedAt = Instant.now();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getLastUsedAt() {
        return lastUsedAt;
    }

    public boolean isValid() {
        return connection.isValid()
                && state != ConnectionState.CLOSED;
    }

    public void close() {

        synchronized (this) {
            if (state == ConnectionState.CLOSED) {
                return;
            }

            state = ConnectionState.CLOSED;
        }

        connection.close();
    }

    @Override
    public String toString() {
        return "PooledConnection{" +
                "id='" + getId() + '\'' +
                ", state=" + state +
                '}';
    }


}
