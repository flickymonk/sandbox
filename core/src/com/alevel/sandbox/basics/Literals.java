package com.alevel.sandbox.basics;

public class Literals {

    public static void main(String[] args) {
        String separator = System.lineSeparator();
        //noinspection OctalInteger
        System.out.println(
                "int:      " + 10_000 + separator +

                "base-2:   " + 0b10 + separator +

                "base-8:   " + 010 + separator +

                "base-16:  " + 0xA + separator +

                "long:     " + 1000000000000000000L + separator +

                "float:    " + 0.0f + separator +

                "double:   " + 0.0d + separator +

                "exponent: " + 1.0e-3d + separator +

                "char:     " + '$' + separator +

                "string:   " + "STR" + separator +

                "boolean:  " + false + " / " + true + separator +

                "null:     " + null
        );
    }
}
