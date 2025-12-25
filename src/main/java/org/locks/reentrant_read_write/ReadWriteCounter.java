package org.locks.reentrant_read_write;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {
    int count = 0;
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void increment(){
        reentrantReadWriteLock.writeLock().lock();
        try{
            count++;
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public int getCount(){
        reentrantReadWriteLock.readLock().lock();
        try{
            return count;
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }


}
