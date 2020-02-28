package com.alevel.sandbox.patterns;

public final class Singleton {

    private static Singleton instance;

    private final String field = "singleton field";

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String getField() {
        return field;
    }
}
