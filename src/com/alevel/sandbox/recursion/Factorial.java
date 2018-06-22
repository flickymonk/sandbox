package com.alevel.sandbox.recursion;

public class Factorial {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "! = " + factorial(i));
        }
    }

    private static long factorial(long n) {
        long result;
        if (n < 1) {
            result = 1;
        } else {
            result = n * factorial(n - 1);
        }
        return result;
    }
}
