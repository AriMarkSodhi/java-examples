package com.ari.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureTest {

    public static void main(String[] args) {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + " " + LocalDateTime.now() + " Hello\n";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + " " + LocalDateTime.now() + " Beautiful\n";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + " " + LocalDateTime.now() + " World\n";
        });

        // create combinedfuture and wait for all to complere
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
        try {
            combinedFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // ensure all complete
        if (!future1.isDone()) System.out.println("future1 not done");
        if (!future2.isDone()) System.out.println("future2 not done");
        if (!future3.isDone()) System.out.println("future3 not done");

        //output result
        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println(combined);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello").thenApplyAsync(s -> s + " World");

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
