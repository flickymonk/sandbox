package com.alevel.sandbox.generics;

import java.util.List;
import java.util.Random;

public final class ExclusiveChoice<T> {

    private final T choice;

    private ExclusiveChoice(T choice) {
        this.choice = choice;
    }

    public static <T> ExclusiveChoice<T> choose1(T value1, T value2) {
        return new ExclusiveChoice<>(null);
    }

    public static <T> ExclusiveChoice<T> choose2(T value1, T value2) {
        return new ExclusiveChoice<>(value2);
    }

    public static <T> ExclusiveChoice<T> chooseRandomly(T value1, T value2) {
        var value = new Random().nextBoolean() ? value1 : value2;
        return new ExclusiveChoice<>(value);
    }

    public T get() {
        return choice;
    }


    public static void main(String[] args) {
        var choice = ExclusiveChoice.chooseRandomly(List.of("str1", "str2"), List.of("str3"));
        System.out.println(choice.get());
    }

}
