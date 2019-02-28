package com.ari.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter implements Callable<String> {

    private static AtomicInteger count = new AtomicInteger();

    @Override
    public String call() throws Exception {
        int lclCount = 0;
        try {
            lclCount = count.getAndIncrement();
            System.out.println("Incrementing counter from: " + lclCount + " by "+ Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Incremented counter by "+ Thread.currentThread().getName()+" to "+Integer.toString(lclCount);
    }

}
