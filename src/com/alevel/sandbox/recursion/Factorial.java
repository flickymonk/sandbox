package com.alevel.sandbox.recursion;

public class Factorial {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "! = " + factorial(i));
        }
    }

    private static long factorial(long n) {
        return n < 1 ? 1 : n * factorial(n - 1);
    }
}
