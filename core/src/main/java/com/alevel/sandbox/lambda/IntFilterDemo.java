package com.alevel.sandbox.lambda;

import java.util.Arrays;

public class IntFilterDemo {

    public static void main(String[] args) {

        int[] result = applyFilter(IntFilter.filter(i -> i % 4 == 0), 0, 2, -3, 4, 8);

        System.out.println(Arrays.toString(result));

        result = applyFilter(IntFilter.filter(i -> i > 0), -1, 1, 0, 5, 4);

        System.out.println(Arrays.toString(result));

        int[] powersOf2 = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

        var powerOf2Filter = IntFilter.filter(i -> Arrays.binarySearch(powersOf2, i) >= 0);

        result = applyFilter(powerOf2Filter,
                0, 4, -3, 5, 2, 7, 8, 1024, 9);

        System.out.println(Arrays.toString(result));

        result = applyFilter(powerOf2Filter.and(i -> i > 100),
                0, 4, -3, 5, 2, 7, 8, 1024, 9);

        System.out.println(Arrays.toString(result));

        var composedFilter = IntFilter.all(
                i -> i > 0,
                i -> i % 2 == 0,
                i -> i < 512
        );

        result = applyFilter(composedFilter, -2, 0, 4, 7, 9, 8, 500, 600);

        System.out.println(Arrays.toString(result));
    }

    private static int[] applyFilter(IntFilter filter, int... ints) {
        return filter.apply(ints);
    }

}
