package com.alevel.sandbox.recursion;

public class Factorial {

    public static void main(String[] args) {
        long fiveFactorial = factorial(5);
        System.out.println(fiveFactorial);
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "! = " + factorial(i));
        }
//        factorial(Long.MAX_VALUE); // will overflow
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
