package org.futuretask;

import java.util.concurrent.*;

public class SumFutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int totalNum = 1000;
        int numThreads = 4;
        int chunk = totalNum/numThreads;

        ExecutorService service = Executors.newFixedThreadPool(numThreads);
        Future<Integer>[] future = new Future[numThreads];
        for(int i=0;i<numThreads;i++){
            int start = i*chunk+1;
            int end = (i == numThreads-1)? totalNum : (i+1)*chunk;

            Callable<Integer> t1 = createSumTask(start,end);
            future[i] = service.submit(t1);
        }

        int totalSum = 0;
        for(Future<Integer> f : future){
            totalSum+=f.get();
        }
        System.out.println("Sum from 1 to " + totalNum + " is: " + totalSum);
        service.shutdown();
    }

    private static Callable<Integer> createSumTask(int start, int end){
        return ()->{
            int sum = 0;
            for(int i=start;i<=end;i++){
                sum+=i;
            }
            System.out.println(Thread.currentThread().getName() + " calculated sum from " + start + " to " + end);
            return sum;
        };
    }
}
