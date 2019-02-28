package com.ari.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentExecutorCompletion {

    private static AtomicInteger threadCount = new AtomicInteger(1);
    private int numThreads = 5;
    private ExecutorService threadPool = Executors.newFixedThreadPool(numThreads, (Runnable r) -> {
        Thread t = new Thread(r, "Thread:"+threadCount.getAndIncrement());
        return t;
    });

    public ConcurrentExecutorCompletion(int numThreads) {
        this.numThreads = numThreads;
    }

    private void submitTasks() throws Exception {
        CompletionService<String> completionService = new ExecutorCompletionService<String>(threadPool);
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        for (int i = 0; i < 20; i++) {
            futureList.add(completionService.submit(new AtomicCounter()));
        }

        threadPool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            threadPool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }

        for (int i = 0; i < 20; i++) {
            Future<String> future = completionService.take();
            System.out.println(future.get());
        }
    }

    public static void main(String args[]) throws Exception {
        ConcurrentExecutorCompletion tpe = new ConcurrentExecutorCompletion(5);
        tpe.submitTasks();
    }

}

