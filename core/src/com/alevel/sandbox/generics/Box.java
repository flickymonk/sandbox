package com.alevel.sandbox.generics;

public class Box<T> {

    T value;

    public Box() {
    }

    public Box(T value) {
        this.value = value;
    }

    public void print() {
        System.out.println("This box contains " + value);
    }

    T getValue() {
        return value;
    }
}
