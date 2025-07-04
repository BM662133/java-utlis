package com.bm.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] workers;

    public CustomThreadPool(int poolSize) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new Thread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            workers[i] = new Worker("Worker-" + i);
            workers[i].start();
        }
    }

    public void submit(Runnable task) {
        try {
            taskQueue.put(task); // blocks if queue is full
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }

        public void run() {
            while (true) {
                try {
                    Runnable task = taskQueue.take(); // waits if empty
                    task.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}