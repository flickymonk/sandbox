package com.alevel.sandbox.patterns;

public class BuilderDemo {
    public static void main(String[] args) {
        Box.BoxBiulder biulder = new Box.BoxBiulder()
                .depth(10)
                .height(10)
                .width(10);
        Box box = biulder.build();
        System.out.println(box);
    }
}
