package com.alevel.sandbox.threads.practice;

import java.util.Scanner;

public class Hippodrome {

    private static final int RACE_LENGTH = 1000;

    public static void main(String[] args) {

        Race race = new Race(RACE_LENGTH);

        Race.Horse[] horses = new Race.Horse[] {
                new Race.Horse("Horse One"),
                new Race.Horse("Horse Two"),
                new Race.Horse("Horse Three"),
                new Race.Horse("Horse Four")
        };

        for (Race.Horse horse : horses) {
            horse.addToRace(race);
        }

        int numberOfHorses = horses.length;

        System.out.println("Bet on a horse:");
        for (int i = 0; i < numberOfHorses; i++) {
            System.out.println(i + 1 + ". " + horses[i]);
        }

        int horseNumber;

        try(Scanner scanner = new Scanner(System.in)) {
            while ((horseNumber = scanner.nextInt()) > numberOfHorses || horseNumber <= 0) {
                System.out.println("No horse with such number is present. Choose once again!");
            }
        }

        Race.Horse chosen = horses[horseNumber - 1];
        System.out.println("You've chosen " + chosen + System.lineSeparator());

        race.startAndWait();

        int place = race.getPlace(chosen);
        System.out.println(System.lineSeparator() + "Horse you had bet on got place " + place + " out of " + numberOfHorses);

    }
}
