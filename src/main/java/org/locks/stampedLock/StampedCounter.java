package org.locks.stampedLock;

import java.util.concurrent.locks.StampedLock;

public class StampedCounter {
    int count = 0;
    StampedLock lock = new StampedLock();

    public void increment(){
        long stamp = lock.writeLock();
        try{
            count++;
        }finally {
            lock.unlockWrite(stamp);
        }
    }

    public int getCount(){
        long stamp = lock.readLock();
        try{
            return count;
        }finally {
            lock.unlockRead(stamp);
        }
    }

    public int getOptimisticLock(){
        long stamp = lock.tryOptimisticRead();
        int value = count;
        if (!lock.validate(stamp)) {
            // fallback to read lock
            stamp = lock.readLock();
            try {
                value = count;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return value;
    }
}


class StampedLockDemo {

    public static void main(String[] args) throws InterruptedException {

        StampedCounter counter = new StampedCounter();

        // Writer thread
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
                System.out.println("Writer incremented");
                sleep(100);
            }
        });

        // Reader thread (optimistic)
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Optimistic read: " + counter.getOptimisticLock());
                sleep(50);
            }
        });

        writer.start();
        reader.start();

        writer.join();
        reader.join();

        System.out.println("Final count: " + counter.getCount());
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

