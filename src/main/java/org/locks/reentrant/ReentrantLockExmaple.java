package org.locks.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExmaple {

    private int count = 0;

    ReentrantLock lock = new ReentrantLock();
    public void increment(){
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }
    }

    public int getCount(){
        lock.lock();
        try{
            return count;
        }finally {
            lock.unlock();
        }
    }
}

class ReentrantExmaple{
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExmaple ex = new ReentrantLockExmaple();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    ex.increment();
                }
            }
        });
        t1.start();
        t1.join();
        System.out.println(ex.getCount());
    }
}
