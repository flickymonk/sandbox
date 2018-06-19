package com.alevel.sandbox.threads.practice;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Hippodrome {

    private static final int RACE_LENGTH = 1000;

    public static void main(String[] args) throws InterruptedException {

        Race race = new Race(RACE_LENGTH);

        Horse[] horses = new Horse[]{
                new Horse("Horse One", race),
                new Horse("Horse Two", race),
                new Horse("Horse Three", race),
                new Horse("Horse Four", race)
        };

        System.out.println("Bet on a horse:");
        for (int i = 0; i < horses.length; i++) {
            System.out.println(i + 1 + ". " + horses[i].getName());
        }
        Scanner scanner = new Scanner(System.in);
        int horseNumber = scanner.nextInt();
        while (horseNumber > horses.length || horseNumber <= 0) {
            System.out.println("No horse with such number is present. Choose once again!");
            horseNumber = scanner.nextInt();
        }

        Horse chosen = horses[horseNumber - 1];
        System.out.println("You've chosen " + chosen.getName() + System.lineSeparator());

        ExecutorService executorService = Executors.newFixedThreadPool(horses.length);

        for (Horse horse : horses) {
            executorService.execute(horse);
        }

        executorService.shutdown();
        executorService.awaitTermination(20, TimeUnit.SECONDS);

        List<Horse> finished = race.getFinished();
        int place = finished.indexOf(chosen) + 1;
        System.out.println(System.lineSeparator() + "Horse you had bet on got place " + place + " out of " + finished.size());

    }
}
