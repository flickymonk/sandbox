package com.alevel.sandbox.algorithms.fib;

import java.util.HashMap;
import java.util.Map;

public class FibonacciCache {

    private static final Map<Integer, Long> fibs = new HashMap<>();

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
        if (fibs.containsKey(n))
            return fibs.get(n);
        long result;
        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            result = fib(n - 1) + fib(n - 2);
        }
        fibs.put(n, result);
        return result;
    }
}
