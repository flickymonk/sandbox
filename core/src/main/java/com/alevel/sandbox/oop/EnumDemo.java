package com.alevel.sandbox.oop;

import java.util.List;
import java.util.Random;

public class EnumDemo {

    public static void main(String[] args) {

        BaseColor[] baseColors = BaseColor.values();

        for (BaseColor baseColor : baseColors) {
            System.out.println(baseColor.ordinal() + ": " + baseColor);
        }

        int index = new Random().nextInt(baseColors.length);
        BaseColor randomColor = baseColors[index];

        String object = switch (randomColor) {
            case RED -> "Apple";
            case GREEN -> "Leaf";
            case BLUE -> "Sky";
        };

        System.out.println(object + " is " + randomColor.name().toLowerCase());

        System.out.println(BaseColor.RED.getHexValue());

        System.out.println(BaseColor.getByHex("#0000ff"));
    }

    enum BaseColor {

        RED("#ff0000"),

        GREEN("#00ff00"),

        BLUE("#0000ff");

        private final String hexValue;

        private static final List<BaseColor> VALUES = List.of(values());

        public static BaseColor getByHex(String hexValue) {
            BaseColor result = null;
            for (BaseColor baseColor : VALUES) {
                String hex = baseColor.hexValue;
                if (hex.equals(hexValue)) {
                    result = baseColor;
                    break;
                }

            }
            return result;
        }

        BaseColor(String hexValue) {
            this.hexValue = hexValue;
        }

        public String getHexValue() {
            return hexValue;
        }
    }

}