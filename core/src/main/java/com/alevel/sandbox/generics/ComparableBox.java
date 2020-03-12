package com.alevel.sandbox.generics;

public class ComparableBox<T extends Comparable<? super T>> extends Box<T> implements Comparable<Box<T>> {

    @Override
    public int compareTo(Box<T> o) {
        return this.value.compareTo(o.value);
    }
}
