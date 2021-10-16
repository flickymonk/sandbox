package com.alevel.sandbox.threads.spammer;

import java.util.ArrayList;

public class Spammer {

    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " RUNNING");

        var workers = new ArrayList<Thread>(5);
        for (int i = 0; i < 5; i++) {
            var thread = new Thread(new SpammerTask());
            workers.add(thread);
            thread.start();
        }

        for (Thread worker : workers) {
            if (Thread.interrupted()) {
                System.err.println(threadName + " was interrupted");
                break;
            }
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("END");
    }

}
