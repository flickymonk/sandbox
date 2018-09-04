package com.alevel.sandbox.collections;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();
        strings.add("foo");
        strings.add("foo");
        strings.add("bazz");
        strings.add("bar");

        for (String string : strings) {
            System.out.println(string);
        }

        System.out.println();

        for (int i = 3; i >= 0; i--) {
            System.out.println(strings.get(i));
        }

        System.out.println(strings.indexOf("bar"));

    }
}
