package com.alevel.sandbox.threads.primecounter;

import java.util.Iterator;

public final class PrimeCounterTask implements Runnable {

    private final Iterator<Integer> numbers;

    private int primes = 0;

    public PrimeCounterTask(Iterator<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public synchronized void run() {
        while (true) {
            int next;
            synchronized (numbers) {
                if (!numbers.hasNext()) break;
                next = numbers.next();
            }
            if (isPrime(next)) {
                primes++;
            }
        }
        System.out.println(Thread.currentThread().getName() + " found " + primes + " prime numbers");
    }

    public synchronized int getCount() {
        return primes;
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2 || number == 3) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 3; i < sqrt; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
