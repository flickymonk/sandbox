package com.alevel.sandbox.algorithms.bitwise;

import java.util.Scanner;

public class PopulationCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long number = scanner.nextLong();

        System.out.println("Binary form: " + Long.toBinaryString(number));

        int populationCount = 0;

        for(long i = number; i != 0; i >>>= 1) {
            populationCount += i & 1;
        }

        System.out.println("Population count of " + number + " is " + populationCount);
    }
}
