package com.alevel.sandbox.oop;

class Parent {

    String tag;

    public Parent(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return tag;
    }

    public String getName(int times) {
        return tag.repeat(times);
    }

}

class Inheritor extends Parent {

    String tag;

    public Inheritor() {
        super("parent");
        tag = "inheritor";
    }

    public String getParentName() {
        return super.tag;
    }

    @Override
    public String getName() {
        return super.getName() + " -> " + tag;
    }

}

public class InheritorDemo {
    public static void main(String[] args) {
        Inheritor inheritor = new Inheritor();
        System.out.println(inheritor.getParentName());
        System.out.println(inheritor.getName());
        Parent parent = inheritor;
        System.out.println(parent.getName(2));
    }
}