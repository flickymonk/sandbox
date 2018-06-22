package com.alevel.sandbox.flow;

import java.util.Scanner;

public class IfStatement {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, input two integer numbers: ");

        int a = scanner.nextInt();
        int b = scanner.nextInt();


        //single branch if
        if (a % 2 == 0)
            System.out.println(b + " is even");

        System.out.println("this was if statement #1");

        if (b % 2 == 0) {
            System.out.println(b + " is even");
            System.out.println("this was if statement #2");
        }

        //if-else
        if (a == b) {
            System.out.println("a and b are equal!");
        } else {
            System.out.println("a and b are not equal!");
        }

        //if-else-if
        if (a > 0) {
            System.out.println("a is positive");
        } else if (a == 0) {
            System.out.println("a is equal to 0");
        } else {
            System.out.println("a is negative");
        }

    }


}
