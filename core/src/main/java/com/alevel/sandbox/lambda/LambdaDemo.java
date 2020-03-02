package com.alevel.sandbox.lambda;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LambdaDemo {
    public static void main(String[] args) {
        Consumer<String> print = s -> System.out.println("Got message: " + s);

        BiConsumer<String, Integer> printNtimes = (s, n) -> {
            for (int i = 0; i < n; i++) {
                print.accept(s);
            }
        };

        UnaryOperator<String> reverse = s -> new StringBuilder(s).reverse().toString();
        print.accept("My message!");
        printNtimes.accept("Hey there", 2);
        String result = reverse.apply("Reverse this");
        System.out.println(result);

        Supplier<String> lazy = () -> "lazy string";

        System.out.println(lazy);

        System.out.println(lazy.get());

        Runnable runnableThatCanFail = LambdaDemo::runCodeThatCanFail;
        runnableThatCanFail.run();
    }

    private static void canThrow() throws Exception {
        System.out.println("I can throw an exception");
    }

    private static void runCodeThatCanFail() {
        try {
            canThrow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
