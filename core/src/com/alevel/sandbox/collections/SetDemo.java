package com.alevel.sandbox.collections;

import java.util.*;

public class SetDemo {
    public static void main(String[] args) {

        Set<String> strings = new HashSet<>();

        strings.add("foo");
        boolean didIAddAnotherFoo = strings.add("foo");
        System.out.println(didIAddAnotherFoo);
        strings.add("bar");
        strings.add("bazz");

        System.out.println(strings.size());

        for (String string : strings) {
            System.out.println(string);
        }

        SortedSet<Integer> sorted = new TreeSet<>();
        sorted.add(10);
        sorted.add(12);
        sorted.add(8);
        System.out.println(sorted);
    }

}
