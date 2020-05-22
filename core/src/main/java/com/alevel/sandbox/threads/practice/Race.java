package com.alevel.sandbox.threads.practice;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

final class Race {

    private final int distance;

    private final Map<Horse, Integer> finished;

    private final Set<Horse> participants;

    private final AtomicInteger placeCounter;

    private final Phaser phaser;

    private final Random random;

    Race(int distance) {
        this.distance = distance;
        finished = new ConcurrentHashMap<>();
        participants = ConcurrentHashMap.newKeySet();
        placeCounter = new AtomicInteger();
        phaser = new Phaser();
        random = new Random();
    }

    int getDistance() {
        return distance;
    }

    int getPlace(Horse horse) {
        Integer place = finished.get(horse);
        if (place == null) {
            throw new IllegalStateException("Horse " + horse + " did not finish the race");
        }
        return place;
    }

    synchronized void startAndWait() {
        placeCounter.set(1);
        finished.clear();
        int numberOfHorses = participants.size();
        phaser.bulkRegister(numberOfHorses);
        int start = phaser.getPhase();
        for (Horse horse : participants) {
            new Thread(horse, horse.name + " Thread").start();
        }
        int finish = phaser.awaitAdvance(start);
        phaser.awaitAdvance(finish);
        System.out.println("All horses finished the race!");
    }

    final static class Horse implements Runnable {

        private static final int MIN_MOVE = 100;
        private static final int MAX_MOVE = 200;

        private static final int MIN_SLEEP = 400;
        private static final int MAX_SLEEP = 500;

        private final String name;

        private Race race;

        private int position;

        Horse(String name) {
            this.name = Objects.requireNonNull(name);
        }

        void addToRace(Race race) {
            removeFromRace();
            this.race = race;
            race.participants.add(this);
        }

        void removeFromRace() {
            if (race == null) return;
            race.participants.remove(this);
        }

        String getName() {
            return name;
        }

        @Override
        public void run() {
            if (race == null) return;
            position = 0;

            System.out.println(name + " is ready!");
            race.phaser.arriveAndAwaitAdvance();
            System.out.println(name + " started the race!");

            int distance = race.distance;
            do {
                move();
                delay();
            } while (position < distance);

            int place = race.placeCounter.getAndIncrement();
            race.finished.put(this, place);
            race.phaser.arriveAndDeregister();
        }

        private void delay() {
            int sleep = randomInBounds(MAX_SLEEP, MIN_SLEEP);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private void move() {
            int move = Math.min(
                    randomInBounds(MAX_MOVE, MIN_MOVE),
                    race.distance - position
            );
            position += move;
            System.out.printf("%s ran %d meters, total of %d%n", name, move, position);
        }

        private int randomInBounds(int max, int min) {
            return race.random.nextInt(max - min + 1) + min;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Horse)) return false;

            Horse horse = (Horse) o;

            return name.equals(horse.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
