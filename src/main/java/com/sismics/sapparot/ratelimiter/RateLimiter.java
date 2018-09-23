package com.sismics.sapparot.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Rate limiter.
 *
 * @author jtremeaux
 */
public class RateLimiter<T> {
    private final ConcurrentHashMap<T, TimerTask> taskMap = new ConcurrentHashMap<>();
    private final Consumer<T> callback;
    private final long interval;

    public RateLimiter(Consumer<T> c, long interval) {
        this.callback = c;
        this.interval = interval;
    }

    public void call(T key) {
        TimerTask task = new TimerTask(key, interval);

        TimerTask prev = taskMap.putIfAbsent(key, task);
        if (prev == null) {
            prev = task;
        }
        prev.run();
    }

    /**
     * The task that wakes up when the wait time elapses.
     *
     */
    private class TimerTask implements Runnable {
        private final T key;
        private long nextTime = 0;
        private long interval;
        private final Object lock = new Object();

        public TimerTask(T key, long interval) {
            this.key = key;
            this.interval = interval;
        }

        public void run() {
            synchronized (lock) {
                long now = System.currentTimeMillis();
                long remaining = nextTime - now;
                if (nextTime == 0 || remaining < 0) {
                    callback.accept(key);
                    nextTime = now + interval;
                }
                // Else ignore task
            }
        }
    }

}