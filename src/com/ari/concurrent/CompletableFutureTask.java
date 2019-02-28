package com.ari.concurrent;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureTask {

    private int max = 0;
    static final ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    public CompletableFutureTask(int max) {
        this.max = max;
    }

    public static void main(String[] args) {
        CompletableFutureTask tasks = new CompletableFutureTask(10);
        long now = System.currentTimeMillis();
        Set<Task> taskResults = tasks.submitTasks();
        long time = System.currentTimeMillis() - now;

        long duration = taskResults.stream()
                .mapToLong(t -> t.getTaskDuration()).sum();
        System.out.println("Executed " + taskResults.size() + " tasks of duration "+duration+" in " + time + " msecs");
        executorService.shutdown();
    }

    public Set<Task> submitTasks() {
        /**
         * Not a lot of value in making this concurrent per number to check
         * - this is more for experimentation
         * - likely faster sequentially in one thread due to overhead of context switch
         */

        List<CompletableFuture<Task>> futures = IntStream.rangeClosed(1, max)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> {
                            Task t = new Task(Double.valueOf(Math.random() * i * 1000).intValue());
                            t.execute();
                            return t;
                        }, executorService)
                ).collect(Collectors.toList());

        return futures.stream().map(CompletableFuture::join).collect(Collectors.toSet());
    }

    class Task {
        long taskDuration;

        public Task(long taskDuration) {
            this.taskDuration = taskDuration;
        }

        public long execute() {
            try {
                Thread.sleep(taskDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return taskDuration;
        }

        public long getTaskDuration() {
            return taskDuration;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Task)) return false;
            Task task = (Task) o;
            return taskDuration == task.taskDuration;
        }

        @Override
        public int hashCode() {
            return Objects.hash(taskDuration);
        }

    }

    public <T> CompletableFuture<List<T>> allAsList(final List<CompletableFuture<T>> futures) {
        return CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[futures.size()])
        ).thenApply(ignored ->
                futures.stream().map(CompletableFuture::join).collect(Collectors.toList())
        );
    }

}