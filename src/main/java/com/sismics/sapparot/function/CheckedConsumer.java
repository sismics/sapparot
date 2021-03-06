package com.sismics.sapparot.function;

@FunctionalInterface
public interface CheckedConsumer<T> {
    void accept(T t) throws Exception;
}
