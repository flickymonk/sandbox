package com.alevel.sandbox.arrays;

import java.util.Arrays;

public class ArrayUtils {

    public static void main(String[] args) {

        String[] colors = {"red", "green", "blue"};

        //toString
        System.out.println(Arrays.toString(colors));

        //deepToString
        int[][] numerics = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(Arrays.toString(numerics));
        System.out.println(Arrays.deepToString(numerics));

        //fill
        int[] filledWith100 = new int[10];
        Arrays.fill(filledWith100, 100);
        System.out.println(Arrays.toString(filledWith100));

        String[] newColors = new String[colors.length];
        //array copy
        System.arraycopy(colors, 0, newColors, 0, colors.length);

        System.out.println(Arrays.equals(colors, newColors));

        //sort
        int[] unsorted = {
                9, 7, 3,
                5, 6, 4,
                2, 8, 1
        };
        Arrays.sort(unsorted);
        System.out.println(Arrays.toString(unsorted));

        //search
        int[] sorted = Arrays.copyOf(unsorted, unsorted.length);
        int positionOf9 = Arrays.binarySearch(sorted, 9);
        System.out.println("position of 9 in array: " + positionOf9);
    }
}
