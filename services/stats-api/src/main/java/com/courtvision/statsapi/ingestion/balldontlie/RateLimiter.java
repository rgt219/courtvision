package com.courtvision.statsapi.ingestion.balldontlie;

import java.util.concurrent.TimeUnit;

public class RateLimiter {

    private final long intervalNanos;
    private long nextAllowedAt = System.nanoTime();

    public RateLimiter(int requestsPerMinute) {
        this.intervalNanos = TimeUnit.MINUTES.toNanos(1) / requestsPerMinute;
    }

    public synchronized void acquire() {
        long now = System.nanoTime();
        long waitNanos = nextAllowedAt - now;
        if (waitNanos > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(waitNanos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while rate limiting", e);
            }
        }
        nextAllowedAt = Math.max(now, nextAllowedAt) + intervalNanos;
    }
}