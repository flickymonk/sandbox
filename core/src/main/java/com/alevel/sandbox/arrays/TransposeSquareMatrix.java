package com.alevel.sandbox.arrays;

import java.util.Arrays;

public class TransposeSquareMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
        };

        System.out.println("Initial");
        printMatrix(matrix);

        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        System.out.println("Transposed");
        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
