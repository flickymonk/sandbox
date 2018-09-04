package com.alevel.sandbox.flow;

import java.util.Scanner;

public class BreakContinue {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //break

        System.out.println("Input a positive integer number");

        while (true) {

            int n = scanner.nextInt();
            if (n > 0) {
                break;
            } else {
                System.out.println("No, this number is not positive. Try again.");
            }

        }

        //continue

        System.out.println("Next, input three positive even numbers");

        int counter = 3;
        while (counter > 0) {
            System.out.println(counter + " remains");

            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("No, this number is not positive. Try again.");
                continue;
            }

            if (n % 2 == 0) {
                counter--;
            } else {
                System.out.println("No, this number is not even. Try again.");
            }

        }

        //break block

        block: {
            for (int i = 0; i < 10; i++) {
                System.out.println(i * 10);
                for (int j = 0; j < 10; j++) {
                    int sum = i * 10 + j;
                    System.out.print(sum + " ");
                    if (sum == 45)
                        break block;
                }
                System.out.println();
            }
        }

    }


}
