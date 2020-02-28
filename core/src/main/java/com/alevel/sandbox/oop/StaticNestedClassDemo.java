package com.alevel.sandbox.oop;

public class StaticNestedClassDemo {

    public static void main(String[] args) {

        Outer.Nested nested = new Outer.Nested("word");

        System.out.println(nested.getField());

    }

}
