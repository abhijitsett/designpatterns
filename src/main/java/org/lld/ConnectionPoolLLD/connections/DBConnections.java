package org.lld.ConnectionPoolLLD.connections;

import org.lld.ConnectionPoolLLD.interfaces.Connection;

import java.util.UUID;

public class DBConnections implements Connection {

    private final String id;
    private boolean open;

    public DBConnections() {
        this.id = UUID.randomUUID().toString();
        this.open = false;
    }


    @Override
    public void open() {
        if (!open) {
            System.out.println(
                    Thread.currentThread().getName()
                            + " opening connection " + id
            );

            open = true;
        }
    }

    @Override
        public void close() {
        if (open) {
            System.out.println(
                    Thread.currentThread().getName()
                            + " closing connection " + id
            );

            open = false;
        }
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getId() {
        return id;
    }
}
