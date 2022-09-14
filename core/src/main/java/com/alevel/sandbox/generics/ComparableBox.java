package com.alevel.sandbox.generics;

import java.util.Comparator;

public class ComparableBox<T extends Comparable<? super T>> extends Box<T> implements Comparable<Box<T>> {


    public ComparableBox(T value) {
        super(value);
    }

    @Override
    public int compareTo(Box<T> o) {
        return this.value.compareTo(o.value);
    }

    public static void main(String[] args) {
        var box1 = new ComparableBox<>("str1");
        var box2 = new ComparableBox<>("str2");

        Comparator<ComparableBox<String>> tComparator = Comparator.reverseOrder();
        System.out.println(tComparator.compare(box1, box2) >= 0 ? box1.value : box2.value);
    }
}
