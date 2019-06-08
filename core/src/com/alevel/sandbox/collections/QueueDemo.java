package com.alevel.sandbox.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {

    public static void main(String[] args) {
        Queue<String> strings = new LinkedList<>();

        strings.add("foo");
        strings.add("foo");
        strings.add("bar");
        strings.add("bazz");

        System.out.println(strings.peek());
        System.out.println();

        while (strings.size() > 0) {
            System.out.println(strings.poll());
        }

        System.out.println();

        Deque<String> stringStack = new ArrayDeque<>(4);
        stringStack.push("foo");
        stringStack.push("foo");
        stringStack.push("bar");
        stringStack.push("bazz");
        while (stringStack.size() > 0) {
            System.out.println(stringStack.pop());
        }
    }
}
