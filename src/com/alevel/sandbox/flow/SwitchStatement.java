package com.alevel.sandbox.flow;

import java.util.Scanner;

public class SwitchStatement {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose color: Red, Blue or Green");

        String color = scanner.next();

        switch (color.toLowerCase()) {
            case "red":
                System.out.println("#ff0000");
                break;
            case "green":
                System.out.println("#00ff00");
                break;
            case "blue":
                System.out.println("#0000ff");
                break;
            default:
                System.out.println("Unknown base color");
        }

    }

}
