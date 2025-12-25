package org.deadlock.fix;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
    static final ReentrantLock lockA = new ReentrantLock();
    static final ReentrantLock lockB = new ReentrantLock();

    public static void main(String[] args) {
        Runnable r1 = () -> {
            boolean lockAAcquired = false;
            boolean lockBAcquired = false;

            try {
                lockAAcquired = lockA.tryLock(1, TimeUnit.MILLISECONDS);
                if (lockAAcquired) {
                    lockBAcquired = lockB.tryLock(1, TimeUnit.MILLISECONDS);
                    if (lockBAcquired) {
                        System.out.println(Thread.currentThread().getName() + " acquired both the lock");
                    }else {
                        System.out.println(Thread.currentThread().getName() + " couldn't acquire lock on Object B");
                    }
                }else{
                    System.out.println(Thread.currentThread().getName() + " couldn't acquire lock on Object A");
                }
            } catch (Exception exception) {
                Thread.currentThread().interrupt();
            } finally {
                if (lockBAcquired)
                    lockB.unlock();
                if (lockAAcquired)
                    lockA.unlock();
            }
        };

        new Thread(r1, "t1").start();
        new Thread(r1, "t2").start();

    }
}
