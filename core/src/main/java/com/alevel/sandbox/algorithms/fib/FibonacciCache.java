package com.alevel.sandbox.algorithms.fib;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FibonacciCache {

    private static final Map<Integer, Long> fibs = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println(fib(11));
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            System.out.print(fib(i) + " ");
        }
        long end = System.nanoTime();
        System.out.println();
        System.out.println("time: " + (end - start));
    }

    private static long fib(int n) {
        return fibs.computeIfAbsent(n, FibonacciCache::countToFib);
    }

    private static long countToFib(int n) {
        long result;
        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            result = fibs.computeIfAbsent(n - 1, FibonacciCache::countToFib) +
                    fibs.computeIfAbsent(n - 2, FibonacciCache::countToFib);
        }
        return result;
    }
}
