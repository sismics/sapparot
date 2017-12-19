package com.sismics.sapparot.function;

@FunctionalInterface
public interface CheckedRunnable {
    void run() throws Exception;
}
