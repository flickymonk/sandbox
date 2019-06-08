package com.alevel.sandbox.generics;

import java.util.Arrays;

public class GenericMethodDemo {
    public static void main(String[] args) {
        String[] cities = {"Kharkiv", "Kiev", "Lviv"};
        String[] citiesDropFirst = dropFirst(cities);
        System.out.println(Arrays.toString(citiesDropFirst));

        Integer[] integers = dropFirst(0, 1, 3, 4);
        System.out.println(Arrays.toString(integers));
    }

    private static <T> T[] dropFirst(T... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        return Arrays.copyOfRange(args, 1, args.length);
    }

}
