package org.deadlock;

public class DeadlockExample {
    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            synchronized (LOCK_A){
                System.out.println("Thread 1: holding LOCK_A");
                sleep();
                synchronized (LOCK_B){
                    System.out.println("Thread 1: holding LOCK_B");
                }
            }
        });

        Thread t2 = new Thread(()->{
            synchronized (LOCK_B){
                System.out.println("Thread 2: holding LOCK_B");
                sleep();
                synchronized (LOCK_A){
                    System.out.println("Thread 2: holding LOCK_A");
                }
            }
        });

        t1.start();
        t2.start();
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
