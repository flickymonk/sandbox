package com.alevel.sandbox.algorithms.fib;

public final class Fibonacci {

    public long fibRecursive(int n) {
        long result;
        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            result = fibRecursive(n - 1) + fibRecursive(n - 2);
        }
        return result;
    }

    public long fibIterative(int n) {
        long result = 0;
        long next = 1;
        for (int i = 0; i < n; i++) {
            long tmp = result;
            result = next;
            next += tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        System.out.println(fib.fibIterative(100));
        System.out.println(fib.fibRecursive(10));
    }

}
