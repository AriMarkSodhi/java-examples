package com.ari.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentScheduledExecutorCompletion {

    private static AtomicInteger threadCount = new AtomicInteger(1);
    private int numThreads = 5;
    private ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(numThreads, (Runnable r) -> {
        Thread t = new Thread(r, "Thread:"+threadCount.getAndIncrement());
        return t;
    });
    private CompletionService<String> completionService = new ExecutorCompletionService<String>(threadPool);


    public ConcurrentScheduledExecutorCompletion(int numThreads) {
        this.numThreads = numThreads;

        threadPool.scheduleAtFixedRate(() -> {
            try {
                submitTasks();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 1, 5, TimeUnit.SECONDS);

    }

    private void submitTasks() throws Exception {
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        for (int i = 0; i < 20; i++) {
            futureList.add(completionService.submit(new AtomicCounter()));
        }

        // waits for each future in turn to complete
        futureList.stream().forEach((future -> {
            if (future.isDone() && !future.isCancelled()) {
                try {
                    String str = future.get();
                    System.out.println(str);

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }));

/*
        for (int i = 0; i < 20; i++) {
            Future<String> future = completionService.take();
            System.out.println(future.get());
        }
*/
    }

    public static void main(String args[]) throws Exception {
        ConcurrentScheduledExecutorCompletion tpe = new ConcurrentScheduledExecutorCompletion(5);
        //tpe.submitTasks();
    }

}

