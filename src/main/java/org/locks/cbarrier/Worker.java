package org.locks.cbarrier;

import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable{

    CyclicBarrier barrier;

    public Worker(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + " loading data");
            Thread.sleep(1000);

            System.out.println(Thread.currentThread().getName() + " waiting for others");
            barrier.await();

            System.out.println("Processing data");

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}

class CyclicBarrierRealWorld{
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> System.out.println("All data loaded. Processing started"));
        for (int i = 0; i < 2; i++) {
            new Thread(new Worker(barrier), "Worker- "+i).start();
        }
    }
}