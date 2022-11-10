package com.alevel.sandbox.threads.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    public static final int DEFAULT_P_AMOUNT = 5;

    private final Lock[] forks;

    public DiningPhilosophers() {
        this(DEFAULT_P_AMOUNT);
    }

    public DiningPhilosophers(int number) {
        forks = new ReentrantLock[number];
        for (int i = 0; i < number; i++) {
            forks[i] = new ReentrantLock();
        }

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) {

        if (philosopher == 0) {

            eat(forks[forks.length - 1], pickLeftFork, putLeftFork, forks[0], pickRightFork, putRightFork, eat);

        } else {

            eat(forks[philosopher], pickRightFork, putRightFork, forks[philosopher - 1], pickLeftFork, putLeftFork, eat);

        }

    }


    private void eat(Lock fork1, Runnable pick1, Runnable put1,
                     Lock fork2, Runnable pick2, Runnable put2,
                     Runnable eat) {

        fork1.lock();
        try {

            fork2.lock();
            try {
                pick1.run();

                pick2.run();

                eat.run();

                put2.run();
            } finally {
                fork2.unlock();
            }

            put1.run();
        } finally {
            fork1.unlock();
        }

    }

}
