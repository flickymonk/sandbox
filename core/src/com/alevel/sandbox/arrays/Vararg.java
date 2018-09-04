package com.alevel.sandbox.arrays;

public class Vararg {

    public static void main(String[] args) {
        int min1 = min(0, 1, 2, 3, -1234, -3244325, 234);
        int min2 = min(0, 1, 2, 3, -1234, -3244325);
        System.out.println(min1);
        System.out.println(min2);
    }

    private static int min(int... ints) {
        int min = ints[0];
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] < min) {
                min = ints[i];
            }
        }
        return min;
    }

}
