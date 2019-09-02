package com.alevel.sandbox.arrays;

import java.util.Arrays;

public class TransposeRectMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1},
                {2, 3},
                {4, 5},
        };

        System.out.println("Initial");
        printMatrix(matrix);

        int maxRowLength = 0;
        for (int i = 0; i < matrix.length; i++) {
            int rowLength = matrix[i].length;
            if (rowLength > maxRowLength) {
                maxRowLength = rowLength;
            }
        }

        int[][] transposedMatrix = new int[maxRowLength][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }

        System.out.println("Transposed");
        printMatrix(transposedMatrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
