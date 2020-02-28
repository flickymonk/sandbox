package com.alevel.sandbox.oop;

public class HiddenWordDemo {

    public static void main(String[] args) {
        HiddenWord defaultPlaceholder = new HiddenWord("hello");
        HiddenWord customPlaceholder = new HiddenWord("hello", '?');

        System.out.println(defaultPlaceholder.display());
        System.out.println(customPlaceholder.display());
    }

}
