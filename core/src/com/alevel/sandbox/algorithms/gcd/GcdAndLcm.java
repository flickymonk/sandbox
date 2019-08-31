package com.alevel.sandbox.algorithms.gcd;

import java.util.Scanner;

public class GcdAndLcm {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();

        int b = scanner.nextInt();

        int gcd;

        // calculating gcd
        {
            int tmpA = a, tmpB = b;

            while (tmpA != 0 && tmpB != 0) {
                if (tmpA > tmpB) {
                    tmpA %= tmpB;
                } else {
                    tmpB %= tmpA;
                }
            }

            gcd = tmpA + tmpB;
        }

        System.out.println("GCD of " + a + " and "+ b + " is " + gcd);

        int lcm = a * b / gcd;

        System.out.println("LCM of " + a + " and "+ b + " is " + lcm);
    }

}
