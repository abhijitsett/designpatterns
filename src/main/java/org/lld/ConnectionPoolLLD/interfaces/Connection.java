package org.lld.ConnectionPoolLLD.interfaces;

public interface Connection {

    void open();

    void close();

    boolean isValid();

    String getId();
}