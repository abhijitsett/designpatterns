package org.locks.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTimeoutExample {
    static Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) {
       Runnable r1 = () ->{
               try{
                   if(semaphore.tryAcquire(10, TimeUnit.MILLISECONDS)){
                      try {
                          System.out.println(Thread.currentThread().getName() + " acquired lock");
                          Thread.sleep(1000);
                      }finally {
                          semaphore.release();
                      }
                   }else{
                       System.out.println(Thread.currentThread().getName() + " didn't acquire lock at all");
                   }
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                   e.printStackTrace();
               }
           };
       new Thread(r1,"t1").start();
       new Thread(r1,"t2").start();
    }
}
