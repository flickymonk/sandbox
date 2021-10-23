package com.alevel.sandbox.threads.reverseprinter;

import java.util.concurrent.Phaser;

public final class ReverseOrderPrintNameTask implements Runnable {

    private final int position;
    private final ReverseOrderPrintNameTask previous;
    private final Phaser barrier;

    public ReverseOrderPrintNameTask(int position,
                                     Phaser barrier,
                                     ReverseOrderPrintNameTask previous) {
        this.position = position;
        this.barrier = barrier;
        this.previous = previous;
    }

    @Override
    public void run() {
        synchronized(this) {
            try {
                if (isLast()) {
                    barrier.arriveAndAwaitAdvance();
                } else {
                    barrier.arrive();
                    wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            print();
        }
        if (previous != null) {
            synchronized(previous) {
                previous.notify();
            }
        }
    }

    private boolean isLast() {
        return position == barrier.getRegisteredParties() - 1;
    }

    private void print() {
        System.out.println("hello from thread " + position);
    }


}
