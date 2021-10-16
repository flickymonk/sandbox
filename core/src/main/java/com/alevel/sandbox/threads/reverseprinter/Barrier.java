package com.alevel.sandbox.threads.reverseprinter;

public final class Barrier {

    private volatile int count;

    private final int size;

    public Barrier(int size) {
        this.count = 0;
        this.size = size;
    }

    public void await() throws InterruptedException {
        if (count < size) {
            synchronized(this) {
                wait();
            }
        }
    }

    public void arrive() {
        if (count < size) {
            synchronized(this) {
                if (++count == size) {
                    notifyAll();
                }
            }
        }
    }

    public int getSize() {
        return size;
    }
}
