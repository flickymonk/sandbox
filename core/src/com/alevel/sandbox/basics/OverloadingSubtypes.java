package com.alevel.sandbox.basics;

import java.io.FileNotFoundException;
import java.io.IOException;

public class OverloadingSubtypes {

    public static void main(String[] args) {
        method(null);
    }

    public static void method(Object value) {
        System.out.println(Object.class.getName());
    }

    public static void method(IOException value) {
        System.out.println(IOException.class.getName());
    }

    public static void method(FileNotFoundException value) {
        System.out.println(FileNotFoundException.class.getName());
    }
}
