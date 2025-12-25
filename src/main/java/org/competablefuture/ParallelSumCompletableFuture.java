package org.competablefuture;

import java.util.concurrent.CompletableFuture;

public class ParallelSumCompletableFuture {
    public static void main(String[] args) {
        int totalNum = 1000;
        int numThreads = 4;
        int chunk = totalNum/numThreads;

        CompletableFuture<Integer>[] futures = new CompletableFuture[numThreads];

        for(int i=0;i<numThreads;i++){
            int start = i*chunk+1;
            int end = (i == numThreads-1) ? totalNum : (i+1)*chunk;

            futures[i] = CompletableFuture.supplyAsync(()->{
                int sum = 0;
                for(int k=start;k<=end;k++){
                    sum+=k;
                }
                System.out.println(Thread.currentThread().getName() + " calculated sum from " + start + " to " + end);
                return sum;
            });
        }

        CompletableFuture<Integer> totalSum = CompletableFuture.allOf(futures).thenApply((v)->{
            int total = 0;
            for(CompletableFuture<Integer> future : futures){
                total+= future.join();
            }
            return total;
        });
        System.out.println("Total sum: " + totalSum.join());
    }
}
