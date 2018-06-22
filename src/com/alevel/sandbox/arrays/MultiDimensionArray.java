package com.alevel.sandbox.arrays;

public class MultiDimensionArray {

    public static void main(String[] args) {
        //noinspection MismatchedReadAndWriteOfArray
        int[][] a = new int[2][2];

        printMatrix(a);

        a = new int[2][];
        a[0] = new int[]{1, 2};
        a[1] = new int[]{3};

        printMatrix(a);

        a = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        printMatrix(a);
    }

    private static void printMatrix(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
