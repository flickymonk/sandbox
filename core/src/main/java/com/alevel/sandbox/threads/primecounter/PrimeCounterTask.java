package com.alevel.sandbox.threads.primecounter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class PrimeCounterTask implements Runnable {

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition completed = lock.newCondition();

    private final List<Integer> numbers;

    private final AtomicInteger itr;

    private final int size;

    private final AtomicInteger primes = new AtomicInteger(0);

    public PrimeCounterTask(List<Integer> numbers, AtomicInteger itr) {
        this.numbers = numbers;
        this.itr = itr;
        this.size = numbers.size();
    }

    @Override
    public void run() {
        if (complete()) return;
        lock.lock();
        try {
            int i;
            while ((i = itr.getAndIncrement()) < size) {
                if (isPrime(numbers.get(i))) {
                    primes.incrementAndGet();
                }
            }
        } finally {
            completed.signalAll();
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " found " + primes + " prime numbers");
    }

    public int getCount() {
        if (!complete()) {
            lock.lock();
            try {
                if (!complete()) {
                    completed.await();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
        return primes.get();
    }

    private boolean complete() {
        return itr.get() >= size;
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
