package com.ari.math;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindPrimes {

    private int max = 0;

    public FindPrimes(int max) {
        this.max = max;
    }

    public static void main(String[] args) {
        FindPrimes primeFinder = new FindPrimes(100000);
        long now = System.currentTimeMillis();
        Set<Integer> primes = primeFinder.findPrimes();
//        Set<Integer> primes = primeFinder.submitTasks();
        List<Integer> primesSorted = new ArrayList<>(primes);
        long time = System.currentTimeMillis() - now;
        Collections.sort(primesSorted, Comparator.comparingInt(o -> o));
        //primesSorted.forEach(System.out::println);
        System.out.println("Found " + primes.size() + " prime numbers in " + time + " msecs");
    }

    public Set<Integer> findPrimesVerbose() {
        /**
         * Not a lot of value in making this concurrent per number to check
         * - this is more for experimentation
         * - likely faster sequentially in one thread due to overhead of context switch
         */
        final ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
            int count = 1;

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "custom-executor-" + count++);
            }
        });

        List<CompletableFuture<Integer>> futures = IntStream.rangeClosed(2, max)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> isPrime(i), executorService)
                ).collect(Collectors.toList());

        return futures.stream().map(CompletableFuture::join).filter(i -> i != 0).collect(Collectors.toSet()); // remove dups in set
    }

    private Integer isPrime(int i) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(i))).filter(j -> ((i / j * j) == i)).map(j -> 0).findAny().orElse(i);
    }

    public Set<Integer> findPrimes() {
        return IntStream.rangeClosed(2, max) // range 2..max
                .parallel() // in parallel - but uses forkjoin pool - not a good idea
                .map(i -> IntStream.rangeClosed(2,  // range 2 .. sqrt if if divisible by number between 2.. sqrt then not prime to map to 0 and filter.
                        (int) (Math.sqrt(i)))
                        .filter(j -> i / j * j == i).map(j -> 0)
                        .findAny().orElse(i))
                .filter(i -> i != 0) // remove if 0
                .mapToObj(i -> Integer.valueOf(i)) // else not 0 convert to INteger
                .collect(Collectors.toSet()); // remove dups in set
    }

}