package com.ari.concurrent;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class WaitingWorker implements Runnable {

    private List<String> outputScraper;
    private CountDownLatch readyThreadCounter;
    private CountDownLatch callingThreadBlocker;
    private CountDownLatch completedThreadCounter;
    private Spliterator<WorkItem> newPartition;

    public WaitingWorker(
            List<String> outputScraper,
            CountDownLatch readyThreadCounter,
            CountDownLatch callingThreadBlocker,
            CountDownLatch completedThreadCounter,
            Spliterator<WorkItem> newPartition) {

        this.outputScraper = outputScraper;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadBlocker = callingThreadBlocker;
        this.completedThreadCounter = completedThreadCounter;
        this.newPartition = newPartition;
    }

    @Override
    public void run() {
        // not thread safe
        outputScraper.add(LocalDateTime.now()+ " readyThreadCounter"+readyThreadCounter.getCount()+"\n");
        readyThreadCounter.countDown();
        try {
            callingThreadBlocker.await();
            while (newPartition.tryAdvance(w -> {
                try {
                    Thread.sleep(w.workload *1000);
                    outputScraper.add(LocalDateTime.now()+ " " +Thread.currentThread().getName()+" worker="+w.getName()+"\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            })) {}

            outputScraper.add(LocalDateTime.now()+ " Counted down\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            completedThreadCounter.countDown();
            outputScraper.add(LocalDateTime.now()+ " completedThreadCounter"+completedThreadCounter.getCount()+"\n");

        }
    }


    public static void main(String[] args) {

        Collection<WorkItem> work = new ArrayList<WorkItem>();
        for (int i = 0; i < 20; i++)
        {
            work.add(new WorkItem("work"+i, Double.valueOf(Math.random()*10.0).intValue()));
        }

        Spliterator<WorkItem> workSpliterator = work.spliterator();

        final List<String> outputScraper= Collections.synchronizedList(new ArrayList<>());;
        try {
            CountDownLatch readyThreadCounter = new CountDownLatch(5);
            CountDownLatch callingThreadBlocker = new CountDownLatch(1);
            CountDownLatch completedThreadCounter = new CountDownLatch(5);
            outputScraper.add(LocalDateTime.now()+ " Create ready\n");
            List<Thread> workers = Stream
                    .generate(() -> new Thread(new WaitingWorker(
                            outputScraper, readyThreadCounter, callingThreadBlocker,
                            completedThreadCounter, workSpliterator.trySplit())))
                    .limit(5)
                    .collect(toList());

            workers.forEach(Thread::start);
            readyThreadCounter.await(); // wait for threads to be ready and blocked on latch
            outputScraper.add(LocalDateTime.now()+ " Workers ready\n");
            callingThreadBlocker.countDown();  // signal threads to run and unblock on latch
            completedThreadCounter.await(); // wait for threads to complete
            outputScraper.add(LocalDateTime.now()+ " Workers complete\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(outputScraper.toString());

    }
}


