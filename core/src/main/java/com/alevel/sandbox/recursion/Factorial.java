package com.alevel.sandbox.recursion;

public class Factorial {

    public static void main(String[] args) {
//        factorial(Integer.MAX_VALUE); // will overflow
        long fiveFactorial = factorial(5);
        System.out.printf("Unoptimized recursion: %d%n", fiveFactorial);
        System.out.printf("%n== MEMOIZATION ==%n");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%d! = %d%n", i, factorialMemoized(i));
        }
        System.out.printf("%n== TABULATION ==%n");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%d! = %d%n", i, factorialTabulated(i));
        }
        System.out.printf("%n== OPTIMIZED TABULATION / ITERATION ==%n");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%d! = %d%n", i, factorialIterated(i));
        }
    }

    private static long factorial(int n) {
        return n < 1 ? 1 : n * factorial(n - 1);
    }

    private static long factorialMemoized(int n) {
        return factorialMemoized(n, new long[n + 1]);
    }

    private static long factorialMemoized(int n, long[] memo) {
        if (memo[n] == 0) {
            memo[n] = (n < 1) ? 1 : n * factorialMemoized(n - 1, memo);
        }
        return memo[n];
    }

    private static long factorialTabulated(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] * i;
        }
        return dp[n];
    }

    private static long factorialIterated(int n) {
        long f = 1;
        for (int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }
}
