package com.alevel.sandbox.generics;

public class WildCardDemo {

    public static void main(String[] args) {
        printHashCodeFromBox(new Box<>(new Foo()));
        printHashCodeFromBox(new Box<>(new Bar()));
//        printHashCodeFromBox(new Box<>(new Bazz()));
        printHashCodeFromBox(new Box<>(new Object()));
    }

    private static <T extends Bar> void printHashCodeFromBox(Box<? super T> box) {
        System.out.println(box.getValue().hashCode());
    }

    private static class Foo {
    }

    private static class Bar extends Foo {
    }

    private static class Bazz {
    }


}
