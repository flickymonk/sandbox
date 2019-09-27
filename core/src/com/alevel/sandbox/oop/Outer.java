package com.alevel.sandbox.oop;

import java.util.Random;

public class Outer {

    private static String outerField = "outer field";

    private final String outerInstanceField = "outer instance field";

    public static class Nested {

        private final String field;

        public Nested(String field) {
            System.out.println(outerField);
            this.field = field + new Random().nextInt(100);
        }

        String getField() {
            return field;
        }
    }

}
