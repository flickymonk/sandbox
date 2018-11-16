package com.alevel.sandbox.lambda;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
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
    }
}
