package com.alevel.sandbox.oop;

public class SuperDemo {

    public static void main(String[] args) {

        B object = new B();

        object.printField();

    }

}

class A {

    String field;

    A(String field) {
        this.field = field;
        System.out.println("Hello from A constructor");
    }


    void printField() {
        System.out.println(field);
    }

}

class B extends A {

    String field = "sub field";

    B() {
        super("field");
        System.out.println("Hello from B constructor");
    }

    @Override
    void printField() {
        super.printField();
        System.out.println(field);
    }

}