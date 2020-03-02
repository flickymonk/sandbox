package com.alevel.sandbox.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("first");
        strings.add("second");
        strings.add("third");
        strings.stream()
                .mapToInt(String::length)
                .mapToObj(StreamDemo::makeSecret)
                .forEach(System.out::println);

        IntStream even = IntStream
                .iterate(2, i -> i + 2);

        List<Integer> first100divBy4 = even.filter(i -> i % 4 == 0)
                .limit(100).boxed().collect(Collectors.toList());

        String first100divBy4String = first100divBy4.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(first100divBy4String);

        String[][] table = {
                {"1-1-1", "1-1-2"}, {"1-2-1", "1-2-2"},
                {"2-1-1", "2-1-2"}, {"2-2-1", "2-2-2"},
                {"3-1-1", "3-1-2"}, {"3-2-1", "3-2-2"},
        };
        Arrays.stream(table).parallel()
                .flatMap(Arrays::stream)
                .forEach(System.out::println);
    }

    private static String makeSecret(int i) {
        return "*".repeat(i);
    }
}
