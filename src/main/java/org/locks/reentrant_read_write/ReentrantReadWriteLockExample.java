package org.locks.reentrant_read_write;

public class ReentrantReadWriteLockExample {
    public static void main(String[] args) throws InterruptedException {

        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable reader = () -> {
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName() + " read : "+counter.getCount());
            }
        };

        Runnable writer = () -> {
            for(int i=0;i<5;i++){
                counter.increment();
                System.out.println(Thread.currentThread().getName() + " wrote");
            }
        };

        new Thread(reader,"Reader-1").start();
        new Thread(reader,"Reader-2").start();
        Thread.sleep(10000);
        new Thread(writer,"Writer-1").start();
    }
}
