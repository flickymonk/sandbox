package com.alevel.sandbox.threads.spammer;

import java.util.concurrent.ThreadLocalRandom;

class SpammerTask implements Runnable {

    private static final int MAX_WAIT = 300;

    private static final int MESSAGES_COUNT = 30;

    @Override
    public void run() {
        for (int i = 0; i < MESSAGES_COUNT; i++) {
            spam(Thread.currentThread().getName() + ": HEYY N" + i);
        }
    }

    private void spam(String message) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(MAX_WAIT));
            System.out.println(message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
