package com.alevel.sandbox.threads.synchronizers;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        final int tasks = 20;

        var counter = new CountDownLatch(tasks);

        Set<String> finished = ConcurrentHashMap.newKeySet(tasks);

        Runnable work = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String name = Thread.currentThread().getName();
            System.out.println(name + " working");
            finished.add(name);
            counter.countDown();
        };

        for (int i = 0; i < tasks; i++) {
            new Thread(work).start();
        }

        System.out.println(finished.size() + " finished");

        counter.await();

        System.out.println(finished.size() + " finished");
    }

}
