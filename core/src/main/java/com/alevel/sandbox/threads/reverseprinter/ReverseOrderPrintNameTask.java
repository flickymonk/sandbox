package com.alevel.sandbox.threads.reverseprinter;

public final class ReverseOrderPrintNameTask implements Runnable {

    private final int position;
    private final ReverseOrderPrintNameTask previous;
    private final Barrier barrier;

    public ReverseOrderPrintNameTask(int position,
                                     Barrier barrier,
                                     ReverseOrderPrintNameTask previous) {
        this.position = position;
        this.barrier = barrier;
        this.previous = previous;
    }

    @Override
    public void run() {
        synchronized(this) {
            barrier.arrive();
            try {
                if (isLast()) {
                    barrier.await();
                } else {
                    this.wait();
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
        return position == barrier.getSize() - 1;
    }

    private void print() {
        System.out.println("hello from thread " + position);
    }


}
