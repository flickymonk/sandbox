package com.alevel.sandbox.threads.synchronizers;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static final class GroupsOfThree {

        private final Semaphore semaphore = new Semaphore(3);

        public void work() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": acquiring resource");
            try {
                semaphore.acquire();
                System.out.println(name + ": work started");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(name + ": work finished");
                semaphore.release();
            }
        }

    }

    public static void main(String[] args) {
        var worker = new GroupsOfThree();

        for (int i = 0; i < 6; i++) {
            new Thread(worker::work).start();
        }
    }
}
