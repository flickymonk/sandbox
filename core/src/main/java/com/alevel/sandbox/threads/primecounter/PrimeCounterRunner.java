package com.alevel.sandbox.threads.primecounter;

import java.util.List;
import java.util.stream.IntStream;

public class PrimeCounterRunner {

    public static void main(String[] args) {

        List<Integer> numbers = IntStream.range(1, 1000).boxed().toList();

        var iterator = numbers.iterator();

        var counter1 = new PrimeCounterTask(iterator);
        var counter2 = new PrimeCounterTask(iterator);

        var worker1 = new Thread(counter1);
        var worker2 = new Thread(counter2);

        worker1.setName("prime-counter-01");
        worker2.setName("prime-counter-02");

        worker1.start();
        worker2.start();

        try {
            worker1.join();
            worker2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        int totalCount = counter1.getCount() + counter2.getCount();

        System.out.println("In total, " + totalCount + " prime numbers were found");
    }

}
