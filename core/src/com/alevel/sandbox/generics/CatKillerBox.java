package com.alevel.sandbox.generics;

import java.util.Random;

class CatKillerBox extends Box<Cat> {

    private final Random rnd = new Random();

    CatKillerBox(Cat value) {
        super(value);
    }

    void randomDeath() {
        final Cat cat = getValue();

        if (rnd.nextBoolean() && cat.isAlive()) {
            cat.kill();
        }

        print();
    }

}
