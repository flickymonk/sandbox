package com.alevel.sandbox.generics;

import java.util.Comparator;

final class Cat {

    public static final Comparator<Cat> COMPARE_BY_AGE = Comparator.comparingInt(Cat::getAge);

    public static final Comparator<Cat> COMPARE_BY_NAME = Comparator.comparing(Cat::getName);

    public static final Comparator<Cat> COMPARE_BY_NAME_IGNORE_CASE =
            (cat1, cat2) -> String.CASE_INSENSITIVE_ORDER.compare(cat1.getName(), cat2.getName());

    private final String name;
    private int age;
    private boolean alive;

    Cat(String name) {
        this.name = name;
        age = 0;
        alive = true;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    void kill() {
        alive = false;
    }

    boolean isAlive() {
        return alive;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", alive=" + alive +
                '}';
    }
}
