package com.alevel.sandbox.algorithms.fizzbuzz;

import java.util.Scanner;

public class DigitsFizzBuzz {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter an integer:");

        int number = scanner.nextInt();

        System.out.println("Fizzing and Buzzing " + number);

        int divisor = 1;

        while (divisor <= number) {
            int digit = number / divisor % 10;

            if (digit % 6 == 0) {
                System.out.println("fizzbuzz");
            } else if (digit % 2 == 0) {
                System.out.println("fizz");
            } else if (digit % 3 == 0) {
                System.out.println("buzz");
            } else {
                System.out.println(digit);
            }

            divisor *= 10;
        }

        System.out.println("Fizzing and buzzing " + number + " left to right");

        divisor = 1_000_000_000;

        while (divisor > 0) {
            if (divisor <= number) {
                int digit = number / divisor % 10;

                if (digit % 6 == 0) {
                    System.out.println("fizzbuzz");
                } else if (digit % 2 == 0) {
                    System.out.println("fizz");
                } else if (digit % 3 == 0) {
                    System.out.println("buzz");
                } else {
                    System.out.println(digit);
                }
            }
            divisor /= 10;
        }
    }

}
