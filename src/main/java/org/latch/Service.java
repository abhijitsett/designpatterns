package org.latch;

import java.util.concurrent.CountDownLatch;

public class Service implements Runnable{

    CountDownLatch latch;

    public Service(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + " starting service");
            Thread.sleep(1000);
        }catch (Exception exception){
            exception.getStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName() + " service started");
            latch.countDown();
        }
    }
}

class ApplicationStartup{
    public static void main(String[] args) {
       CountDownLatch latch = new CountDownLatch(3);

       new Thread(new Service(latch),"DB").start();
       new Thread(new Service(latch), "Cache").start();
       new Thread(new Service(latch), "MessageQueue").start();
    }
}