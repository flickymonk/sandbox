package com.alevel.sandbox.patterns;

public class FactoryMethodDemo {

    public static void main(String[] args) {
        Box cube = Box.create(10);
        Box invalidBox = Box.create(-1);
        System.out.println(cube);
        System.out.println(invalidBox);
    }

}
