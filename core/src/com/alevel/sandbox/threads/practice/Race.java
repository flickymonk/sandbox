package com.alevel.sandbox.threads.practice;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class Race {

    private final int distance;

    private final Map<Horse, Integer> finished;

    private final Set<Horse> participants;

    private CountDownLatch countDownLatch;

    private final AtomicInteger placeCounter;

    private final Lock lock;

    Race(int distance) {
        this.distance = distance;
        finished = new ConcurrentHashMap<>();
        participants = ConcurrentHashMap.newKeySet();
        placeCounter = new AtomicInteger();
        lock = new ReentrantLock(true);
    }

    int getDistance() {
        return distance;
    }

    public void startAndWait() {
        try {
            lock.lock();
            start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private void start() throws InterruptedException {
        placeCounter.set(0);
        finished.clear();
        int numberOfHorses = participants.size();
        countDownLatch = new CountDownLatch(numberOfHorses);
        for (Horse horse : participants) {
            new Thread(horse, horse.name + " Thread").start();
        }
        countDownLatch.await();
        participants.clear();
    }

    private void finish(Horse horse) {
        int place = placeCounter.incrementAndGet();
        finished.put(horse, place);
        countDownLatch.countDown();
    }

    public int getPlace(Horse horse) {
        Integer place = finished.get(horse);
        if (place == null) {
            throw new IllegalStateException("Horse " + horse + " did not finish the race");
        }
        return place;
    }

    final static class Horse implements Runnable {

        private static final int MIN_MOVE = 100;
        private static final int MAX_MOVE = 200;

        private static final int MIN_SLEEP = 400;
        private static final int MAX_SLEEP = 500;

        private final Random random = new Random();

        private final String name;

        private Race race;

        private int position;

        Horse(String name) {
            this.name = Objects.requireNonNull(name);
        }

        void addToRace(Race race) {
            this.race = race;
            race.participants.add(this);
        }

        @Override
        public void run() {
            if (race == null) return;
            position = 0;
            System.out.println(name + " started the race!");
            int distance = race.distance;
            do {
                delay();
                move();
            } while (position < distance);
            race.finish(this);
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
            System.out.println(name + " ran " + move + " meters, total of " + position);
        }

        private int randomInBounds(int max, int min) {
            return random.nextInt(max - min + 1) + min;
        }

        String getName() {
            return name;
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
