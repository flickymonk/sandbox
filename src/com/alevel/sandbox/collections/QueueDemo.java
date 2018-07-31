package com.alevel.sandbox.collections;

import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {

    public static void main(String[] args) {
        Queue<String> strings = new LinkedList<>();

        strings.add("foo");
        strings.add("foo");
        strings.add("bar");
        strings.add("bazz");

        while (strings.size() > 0) {
            System.out.println(strings.poll());
        }
    }
}
