package com.alevel.sandbox.generics;

public class BoxWeCanEmpty<T> extends Box<T> {
    void empty() {
        value = null;
    }
}
