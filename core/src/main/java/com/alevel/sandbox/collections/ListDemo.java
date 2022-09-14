package com.alevel.sandbox.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {

        List<String> strings = new ArrayList<>(4);
        strings.add("foo");
        strings.add("foo");
        strings.add("bazz");
        strings.add("bar");

        new LinkedList<String>();

        for (String string : strings) {
            System.out.println(string);
        }

        System.out.println();

        strings.remove(1);

        for (int i = strings.size() - 1; i >= 0; i--) {
            System.out.println(strings.get(i));
        }

        System.out.println(strings.indexOf("bar"));

    }
}
