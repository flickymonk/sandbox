package com.alevel.sandbox.oop;

import java.util.Random;

public class EnumDemo {
    public static void main(String[] args) {

        BaseColor[] baseColors = BaseColor.values();

        for (BaseColor baseColor : baseColors) {
            System.out.println(baseColor.ordinal() + ": " + baseColor);
        }

        BaseColor randomColor = baseColors[new Random().nextInt(baseColors.length)];

        switch (randomColor) {
            case RED:
                System.out.print("Apple");
                break;
            case GREEN:
                System.out.print("Leaf");
                break;
            case BLUE:
                System.out.print("Sky");
                break;
        }

        System.out.println(" is " + randomColor.name().toLowerCase());

    }
}

enum BaseColor {
    RED, GREEN, BLUE
}