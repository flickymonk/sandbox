package com.alevel.sandbox.basics;

import java.util.Arrays;

public class Methods {
    public static void main(String[] args) {
        int i = 1;
        System.out.println("i: " + i);

        increment(i);
        System.out.println("incremented, not returned: " + i);

        System.out.println("incremented, returned: " + increment(i));

        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("numbers: " + Arrays.toString(numbers));

        increment(numbers);
        System.out.println("numbers: " + Arrays.toString(numbers));
    }

    private static int increment(int number) {
        return ++number;
    }

    private static void increment(int... numbers) {
        int length = numbers.length;

        if (length == 0) return;

        for (int i = 0; i < length; i++) {
            numbers[i]++;
        }
    }
}
