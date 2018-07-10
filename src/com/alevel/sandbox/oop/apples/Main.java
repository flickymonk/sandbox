package com.alevel.sandbox.oop.apples;

public class Main {

    public static void main(String[] args) {

        Apple[] apples = {
                new Apple(),
                new Apple(),
                new RottenApple(1),
                new Apple(),
                new RottenApple(2),
                new Apple()
        };

        findRottenApples(apples);
    }

    private static void findRottenApples(Apple... apples) {
        for (int i = 0; i < apples.length; i++) {
            Apple apple = apples[i];
            if (apple instanceof RottenApple) {
                RottenApple ra = (RottenApple) apple;
                System.out.println("Apple " + i + " is rotten, it has " +
                        ra.getWormCount() + " worms in it!");
            }
        }
    }


}
