package com.bm.retry;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class RetryExecutorTest {

    @Test
    public void testRetrySuccessOnFirstTry() {
        String result = RetryExecutor.executeWithRetry(() -> "Success", 3, 100);
        assertEquals("Success", result);
    }

    @Test
    public void testRetrySucceedsAfterFailures() {
        AtomicInteger attempt = new AtomicInteger(0);

        String result = RetryExecutor.executeWithRetry(() -> {
            if (attempt.incrementAndGet() < 3) {
                throw new RuntimeException("Fail");
            }
            return "Recovered";
        }, 5, 100);

        assertEquals("Recovered", result);
        assertEquals(3, attempt.get());
    }

    @Test
    public void testRetryFailsAfterMaxAttempts() {
        AtomicInteger attempt = new AtomicInteger(0);

        Exception exception = assertThrows(RuntimeException.class, () ->
            RetryExecutor.executeWithRetry(() -> {
                attempt.incrementAndGet();
                throw new RuntimeException("Fail always");
            }, 3, 100)
        );

        assertEquals(4, attempt.get()); // initial try + 3 retries
        assertEquals("Fail always", exception.getMessage());
    }
}