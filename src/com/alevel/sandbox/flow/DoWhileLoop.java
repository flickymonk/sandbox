package com.alevel.sandbox.flow;

public class DoWhileLoop {

    public static void main(String[] args) {

        int i = 0;

        //noinspection ConstantConditions
        do {
            System.out.println("i = " + i--);
        } while (i > 0);


    }

}
