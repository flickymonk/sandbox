package com.alevel.sandbox.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        Iterable<String> strings = List.of("one", "two", "three");

        Iterator<String> iterator = strings.iterator();

        //noinspection WhileLoopReplaceableByForEach
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println();

        for (String string : strings) {
            System.out.println(string);
        }
    }
}
