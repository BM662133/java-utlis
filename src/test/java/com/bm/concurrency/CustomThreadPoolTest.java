package com.bm.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class CustomThreadPoolTest {

    @Test
    public void testSubmitTasks() throws InterruptedException {
        CustomThreadPool pool = new CustomThreadPool(3);
        AtomicInteger counter = new AtomicInteger(0);

        for (int i = 0; i < 10; i++) {
            pool.submit(() -> {
                counter.incrementAndGet();
                try {
                    Thread.sleep(100); // simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Wait for tasks to complete
        Thread.sleep(1500);
        assertEquals(10, counter.get());
    }
}
