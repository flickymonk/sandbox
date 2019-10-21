package com.alevel.sandbox.collections.practice;

public class ForwardLinkedListDemo {

    public static void main(String[] args) {
        ForwardLinkedList<String> strings = new ForwardLinkedList<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        System.out.println(strings);
        for (String string : strings) {
            System.out.println(string);
        }
        int size = strings.size();
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            System.out.println(strings.get(i));
        }
        System.out.println("removing");
        strings.remove(0);
        strings.remove(1);
        System.out.println(strings);
        strings.add(0, "one");
        strings.add(1, "one-and-half");
        System.out.println(strings);

        ForwardLinkedList<String> comparing = new ForwardLinkedList<>();
        for (String string : strings) {
            comparing.add(string);
        }
        System.out.println(comparing.equals(strings));
        comparing.add("new element");
        System.out.println(comparing.equals(strings));

        strings.clear();
        System.out.println(strings);
    }
}
