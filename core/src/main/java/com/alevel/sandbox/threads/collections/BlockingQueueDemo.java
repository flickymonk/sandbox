package com.alevel.sandbox.threads.collections;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) {

        var queue = new LinkedBlockingQueue<String>();

        var scanner = new Scanner(System.in);

        var producer = new Thread(() -> {
            String value;
            while (!(value = scanner.nextLine()).isEmpty()) {
                queue.addAll(Arrays.asList(value.split("\\s")));
            }
            queue.add("");
        });

        var consumer = new Thread(() -> {
            String value;
            try {
                while (!(value = queue.take()).isEmpty()) {
                    System.out.printf("echo: %s%n", value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }

}
