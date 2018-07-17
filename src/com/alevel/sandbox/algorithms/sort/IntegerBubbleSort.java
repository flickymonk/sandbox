package com.alevel.sandbox.algorithms.sort;

import java.util.Arrays;

public class IntegerBubbleSort implements IntegerSortingAlgorithm {

    @Override
    public void sort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {

            for (int j = 0; j < array.length - i - 1; j++) {

                System.out.println(">> " + Arrays.toString(array));

                int k = j + 1;
                if (array[j] > array[k]) {
                    //swap
                    array[j] = array[j] ^ array[k];
                    array[k] = array[j] ^ array[k];
                    array[j] = array[j] ^ array[k];
                }
            }
        }
    }

}
