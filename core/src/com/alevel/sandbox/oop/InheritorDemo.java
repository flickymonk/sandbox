package com.alevel.sandbox.oop;

class Parent {

    String tag = "parent";

}

class Inheritor extends Parent {

    public String getParentName() {
        return super.tag;
    }

}

public class InheritorDemo {
    public static void main(String[] args) {
        Inheritor inheritor = new Inheritor();
        System.out.println(inheritor.getParentName());
    }
}