package com.ari.concurrent;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class ProducerConsumer {

    // Create a list shared by producer and consumer
    // Size of list is 2.
    private LinkedList<Integer> list = new LinkedList<>();
    private int capacity;
    private Semaphore sema = new Semaphore(1);

    public ProducerConsumer(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args)
            throws InterruptedException {
// Object of a class that has both produce()
// and consume() methods
        final ProducerConsumer pc = new ProducerConsumer(2);

        // Create producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
    }

    // Function called by producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            try {
                sema.acquire();
                // producer thread waits while list
                // is full
                if (list.size() == capacity)
                    continue;

                System.out.println("Producer produced-"
                        + value);

                // to insert the jobs in the list
                list.add(value++);
            } finally {
                sema.release();
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            try {
                sema.acquire();
                // consumer thread waits while list
                // is empty
                if (list.size() == 0)
                    continue;

                //to retrive the ifrst job in the list
                int val = list.removeFirst();

                System.out.println("Consumer consumed-"
                        + val);
            } finally {
                sema.release();
            }
        }


    }
}
