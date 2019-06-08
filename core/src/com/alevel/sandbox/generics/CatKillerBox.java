package com.alevel.sandbox.generics;

import java.util.Random;

public class CatKillerBox extends Box<Cat> {

    public CatKillerBox(Cat value) {
        super(value);
    }

    private final Random rnd = new Random();

    public void randomDeath() {
        if(rnd.nextBoolean()) {
            getValue().kill();
        }
        print();
    }

}
