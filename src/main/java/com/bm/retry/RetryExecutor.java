package com.bm.retry;

import java.util.function.Supplier;

public class RetryExecutor {

    public static <T> T executeWithRetry(Supplier<T> task, int maxRetries, long delayMs) {
        int attempt = 0;

        while (true) {
            try {
                return task.get();
            } catch (Exception e) {
                attempt++;
                if (attempt > maxRetries) {
                    throw e;
                }
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", ie);
                }
            }
        }
    }
}