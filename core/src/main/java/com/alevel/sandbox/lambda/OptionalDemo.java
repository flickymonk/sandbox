package com.alevel.sandbox.lambda;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> something = Optional.of("something");
        Optional<String> nothing = Optional.empty();
        Optional<String> maybeSomething = maybeNull();

        Integer value = something
                .filter(s -> s.length() > 0)
                .flatMap(OptionalDemo::removeTheThing)
                .or(OptionalDemo::maybeNull)
                .map(String::length)
                .orElseThrow(() -> new IllegalArgumentException("do no such thing!"));
        System.out.println(value);

        something.ifPresent(System.out::println);
        nothing.map(s -> {
            System.out.println("hi from side effect");
            return s.charAt(0);
        }).ifPresent(System.out::println);

        Optional<String> four = Stream.of("one", "two", "three")
                .filter(Predicate.isEqual("four"))
                .findAny();
        four.ifPresent(System.out::println);

        String somethingOrElse = maybeSomething.orElse(getRandomString());
        String somethingOrElseGet = maybeSomething.orElseGet(OptionalDemo::getRandomString);
        System.out.println(somethingOrElse);
        System.out.println(somethingOrElseGet);
        String somethingOrElseThrow = maybeSomething.orElseThrow(() -> new RuntimeException("We have nothing!"));
        System.out.println(somethingOrElseThrow);

    }

    private static String getRandomString() {
        String s = UUID.randomUUID().toString();
        System.out.println("new rnd string generated: " + s);
        return s;
    }

    private static Optional<String> removeTheThing(String s) {
        s = s.replace("thing", "");
        return s.isEmpty() ? Optional.empty() : Optional.of(s);
    }

    private static Optional<String> maybeNull() {
        return Optional.ofNullable(new Random().nextBoolean() ? "non-null" : null);
    }
}
