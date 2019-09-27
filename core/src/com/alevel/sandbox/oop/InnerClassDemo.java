package com.alevel.sandbox.oop;

public class InnerClassDemo {

    public static void main(String[] args) {

        Dictionary dict = new Dictionary("en");

        Dictionary.Word cat = dict.defineWord("cat", "a domestic animal");

        Dictionary ruDict = new Dictionary("ru");
        Dictionary.Word ruCat = ruDict.defineWord("кот", "домашнее животное");

        System.out.println(cat);
        System.out.println(ruCat);

        //method local class

        class MethodLocalClass {

            private void printWords() {
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

        Object o = new Object() {
            private Object sayHello() {
                System.out.println("Hello from anonymous class");
                return this;
            }
        }.sayHello();

        System.out.println(o.getClass());

        Runnable outputJavaHome = () -> System.out.println(System.getenv("JAVA_HOME"));

        outputJavaHome.run();
    }

}
