package com.alevel.sandbox.algorithms.sort;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("This program will sort an array A of length L, containing integer numbers");

        final String endl = System.lineSeparator();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the sorting algorithm: " + endl +
                "bubble -> Bubble Sort " + endl +
                "insert -> Insert Sort " + endl +
                "quick  -> Quick Sort");

        IntegerSortingAlgorithm sortingAlgorithm = null;

        while (sortingAlgorithm == null) {
            String userInput = scanner.next().trim().toLowerCase();
            switch (userInput) {
                case "bubble":
                    sortingAlgorithm = new IntegerBubbleSort();
                    break;
                case "insert":
                    sortingAlgorithm = new IntegerInsertSort();
                    break;
                case "quick":
                    sortingAlgorithm = new IntegerQuickSort();
                    break;
            }
        }

        System.out.println("Enter L");

        final int l = scanner.nextInt();

        int[] a = new int[l];

        System.out.println("Enter A");

        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }

        System.out.println("Unsorted array: " + Arrays.toString(a));

        sortingAlgorithm.sort(a);

        System.out.println("Sorted array: " + Arrays.toString(a));

    }

}
