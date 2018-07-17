package com.alevel.sandbox.algorithms.sort;

import java.util.Arrays;

public class IntegerInsertSort implements IntegerSortingAlgorithm {

    @Override
    public void sort(int[] array) {

        for (int i = 1; i < array.length; ++i) {
            System.out.println(">> " + Arrays.toString(array));

            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
