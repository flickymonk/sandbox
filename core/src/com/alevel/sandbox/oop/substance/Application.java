package com.alevel.sandbox.oop.substance;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.println("Enter a substance:\n1: Iron\n2: Water\n3: Oxygen");

        Scanner scanner = new Scanner(System.in);

        int substanceCode = scanner.nextInt();

        Substance substance;

        switch (substanceCode) {
            case 1:
                substance = new Iron();
                break;
            case 2:
                substance = new Water();
                break;
            case 3:
                substance = new Oxygen();
                break;
            default:
                System.err.println("Unknown substance!");
                return;
        }

        double t;
        while ((t = scanner.nextDouble()) != 0.0) {
            double initialT = substance.getTemperature();
            State state = substance.heatUp(t);
            double newT = substance.getTemperature();

            System.out.println("We changed the t of " + substance.getName() +
                    " from " + initialT + " to " + newT +
                    ", its state is now - " + state);
        }

        System.out.println("Bye!");
    }
}
