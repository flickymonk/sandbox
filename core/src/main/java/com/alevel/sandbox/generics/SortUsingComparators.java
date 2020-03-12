package com.alevel.sandbox.generics;

import java.util.Arrays;

public class SortUsingComparators {
    public static void main(String[] args) {
        Cat[] cats = { new Cat("cat1"), new Cat("cat2"), new Cat("cat0") };
        Arrays.sort(cats, Cat.COMPARE_BY_NAME_IGNORE_CASE);
        System.out.println(Arrays.toString(cats));
    }
}
