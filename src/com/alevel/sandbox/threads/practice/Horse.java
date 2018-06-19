package com.alevel.sandbox.threads.practice;

import java.util.Random;

final class Horse implements Runnable {

    private static final int MIN_MOVE = 100;
    private static final int MAX_MOVE = 200;

    private static final int MIN_SLEEP = 400;
    private static final int MAX_SLEEP = 500;

    private final Random random = new Random();

    private final String name;

    private final Race race;

    private final int raceLength;

    private int position = 0;

    Horse(String name, Race race) {
        this.name = name;
        this.race = race;
        raceLength = race.getLength();
    }

    @Override
    public void run() {
        System.out.println(getName() + " started the race!");
        do {
            delay();
            move();
        } while (position < raceLength);
        race.finish(this);
    }

    private void delay() {
        int sleep = random.nextInt(MAX_SLEEP + 1 - MIN_SLEEP) + MIN_SLEEP;
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            System.err.println(getName() + " fell: " + e.getLocalizedMessage());
        }
    }

    private void move() {
        int move = random.nextInt(MAX_MOVE + 1 - MIN_MOVE) + MIN_MOVE;
        if (move + position > raceLength) {
            move = raceLength - position;
        }
        position += move;
        System.out.println(getName() + " ran " + move + " meters, total of " + position);
    }

    public String getName() {
        return name;
    }
}
