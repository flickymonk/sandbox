package com.alevel.sandbox.flow;

public class ForLoop {

    public static void main(String[] args) {
        System.out.println("let's count to 10");

        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println();

        System.out.println("now, let's do something different");

        for (int i = 0, j = 0; i <= 10; i++, j = i * i){
            System.out.println(i + " squared = " + j);
        }

    }

}
