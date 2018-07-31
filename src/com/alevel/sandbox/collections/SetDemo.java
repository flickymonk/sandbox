package com.alevel.sandbox.collections;

import java.util.HashSet;
import java.util.Set;

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

    }

}
