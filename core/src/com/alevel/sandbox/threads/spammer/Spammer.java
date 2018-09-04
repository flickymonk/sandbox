package com.alevel.sandbox.threads.spammer;

public class Spammer {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " RUNNING");

        for (int i = 0; i < 5; i++) {
            new Thread(new SpammerTask()).start();
        }
    }

}
