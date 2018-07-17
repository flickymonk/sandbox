package com.alevel.sandbox.exceptions;

import java.util.Scanner;

public class Divisor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter two numbers");

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        if (b == 0) {
            throw new IllegalArgumentException("Second Operand is 0!");
        }

        int result = a / b;
        System.out.println("a / b = " + result);
    }
}
