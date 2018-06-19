package com.alevel.sandbox.threads.practice;

import java.util.ArrayList;
import java.util.List;

final class Race {

    private final int length;

    private final List<Horse> finished = new ArrayList<>();

    Race(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public synchronized void finish(Horse horse) {
        System.out.println(horse.getName() + " finished the race!");
        finished.add(horse);
    }

    public List<Horse> getFinished() {
        return finished;
    }
}
