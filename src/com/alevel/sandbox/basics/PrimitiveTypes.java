package com.alevel.sandbox.basics;

public class PrimitiveTypes {

    public static void main(String[] args) {

        //here are all the primitive types

        byte b = (byte) 128;

        short s = (short) 32768;

        int i = 0;

        long l = 0L;

        float f = 0.0f;

        double d = 0.0d;

        char c = 'c';

        boolean bool = false;

        //all other types are object types

        String separator = System.lineSeparator();
        System.out.println(
                "byte:    " + b + separator +
                "short:   " + s + separator +
                "int:     " + i + separator +
                "long:    " + l + separator +
                "float:   " + f + separator +
                "double:  " + d + separator +
                "char:    " + c + separator +
                "boolean: " + bool + separator
        );

    }


}
