package com.alevel.sandbox.patterns;

public class PrototypeDemo {
    public static void main(String[] args) {
        Box b1 = new Box.BoxBiulder()
                .build();
        Box b2 = b1.clone();
        System.out.println(b1.equals(b2));
        System.out.println(b1 == b2);
    }
}
