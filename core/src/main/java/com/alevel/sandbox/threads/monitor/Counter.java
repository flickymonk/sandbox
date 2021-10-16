package com.alevel.sandbox.threads.monitor;

public class Counter {

    private volatile int value = 0;

    public synchronized int next() {
        return value++;
    }

}
