package com.alevel.sandbox.oop;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntBinaryOperator;

public class InnerClassDemo {

    public static void main(String... args) {

        Dictionary dict = new Dictionary("en");

        Dictionary.Word cat = dict.defineWord("cat", "a domestic animal");

        Dictionary ruDict = new Dictionary("ru");
        Dictionary.Word ruCat = ruDict.defineWord("кот", "домашнее животное");

        System.out.println(cat);
        System.out.println(ruCat);

        //method local class

        class MethodLocalClass {

            void printWords() {
                System.out.println(cat.getWord());
                System.out.println(ruCat.getWord());
            }

        }
        MethodLocalClass local = new MethodLocalClass();
        local.printWords();

        //anonymous class

        Dictionary pirate = new Dictionary("pirate") {

            @Override
            public String getLanguage() {
                m();
                return "ARRR";
            }

            void m() {
                System.out.println("anon class method");
            }

        };

        System.out.println(pirate.getClass());
        System.out.println(pirate.getLanguage());

        var o = new Object() {
            private Object sayHello() {
                System.out.println("Hello from anonymous class");
                return this;
            }
        };
        o.sayHello();

        System.out.println(o.getClass());

        Runnable outputJavaHome = () -> System.out.println(System.getenv("JAVA_HOME"));

        outputJavaHome.run();

        DoubleBinaryOperator sum = Double::sum;

        System.out.println(sum.applyAsDouble(4.0, 3.0));

        DoubleUnaryOperator negation = a -> -a;
        System.out.println(negation.applyAsDouble(10));

        System.out.println(reduce(Integer::sum, 0, 1, 2, 3, 4, 5));
        System.out.println(reduce(Math::max, Integer.MIN_VALUE, 9, -3, 123, 10, 8, -3));
    }

    private static int reduce(IntBinaryOperator function, int identity, int... ints) {
        int result = identity;
        for (int i : ints) {
            result = function.applyAsInt(result, i);
        }
        return result;
    }

}
