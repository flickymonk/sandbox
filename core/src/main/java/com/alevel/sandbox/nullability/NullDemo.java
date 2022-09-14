package com.alevel.sandbox.nullability;

import java.util.Objects;

public class NullDemo {


    public static void main(String[] args) {
        Object nothing = null;
        String nullString = ((String) nothing);
//        System.out.println(nullString.length());
        System.out.println(myMethod(null));
    }

    public static int myMethod(Object... value) {
        Objects.requireNonNull(value, "value should not be null");
        return value.length;
    }

}



class A {

    private Objects f1;

    private Objects f2;

}

class B extends A {


    long m1() {
        return System.currentTimeMillis();
    }


}