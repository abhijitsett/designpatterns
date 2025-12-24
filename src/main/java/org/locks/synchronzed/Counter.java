package org.locks.synchronzed;

public class Counter{

    private int count = 0;

    public synchronized void increment(){
        count++;
    }

    public synchronized int getCount(){
            return count;
    }

}

class CounterTest {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        });
        t1.start();
        t1.join();
        System.out.println(counter.getCount());
    }

}