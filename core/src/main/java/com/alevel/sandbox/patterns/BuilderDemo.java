package com.alevel.sandbox.patterns;

public class BuilderDemo {
    public static void main(String[] args) {
        Box.BoxBuilder bb = new Box.BoxBuilder()
                .depth(10)
                .height(10);
        Box box = bb.width(10).build();
        System.out.println(box);
    }
}
