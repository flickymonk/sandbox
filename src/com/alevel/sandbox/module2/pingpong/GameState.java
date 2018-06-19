package com.alevel.sandbox.module2.pingpong;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

final class GameState {

    private final AtomicBoolean isOver = new AtomicBoolean(false);

    private final AtomicReference<String> input = new AtomicReference<>();

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    void finishGame() {
        if (isOver.compareAndSet(false, true)) {
            countDownLatch.countDown();
        }
    }

    void awaitGameOver() throws InterruptedException {
        countDownLatch.await();
    }

    boolean isOver() {
        return isOver.get();
    }

    public AtomicReference<String> getInput() {
        return input;
    }
}
