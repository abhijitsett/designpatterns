package org.deadlock.fix;

public class LockOrdering {
    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        Runnable r1 = () -> {
            synchronized (LOCK_A) {
                synchronized (LOCK_B) {
                    System.out.println(Thread.currentThread().getName() + " acquired both");
                }
            }
        };

        new Thread(r1, "t1").start();
        new Thread(r1, "t2").start();
    }
}
