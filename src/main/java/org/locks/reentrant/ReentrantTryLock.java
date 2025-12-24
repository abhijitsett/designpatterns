package org.locks.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTryLock {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        boolean acquired = false;
        try {
            acquired = lock.tryLock(500, TimeUnit.MILLISECONDS);
            if (acquired) {
                count++;
            } else {
                System.out.println(Thread.currentThread().getName()
                        + " could not acquire lock");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (acquired) {
                lock.unlock();
            }
        }
    }

    public int getCount() {
        return count; // safe after joins
    }
}

class ReentrantExample {
    public static void main(String[] args) throws InterruptedException {

        ReentrantTryLock ex = new ReentrantTryLock();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ex.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ex.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(ex.getCount()); // 10
    }
}
