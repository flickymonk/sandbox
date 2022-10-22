package com.alevel.sandbox.threads.doublecheck;

import java.util.Objects;
import java.util.function.Supplier;

public final class Memo<T> implements Supplier<T> {

    private final Supplier<T> source;

    private volatile boolean initialized;

    private T value;

    private Memo(Supplier<T> source) {
        this.source = source;
    }

    public static <T> Memo<T> of(Supplier<T> supplier) {
        Objects.requireNonNull(supplier);
        return new Memo<>(supplier);
    }

    @Override
    public T get() {
        if (!initialized) {
            synchronized (this) {
                if (!initialized) {
                    value = source.get();
                    initialized = true;
                }
            }
        }
        return value;
    }
}
