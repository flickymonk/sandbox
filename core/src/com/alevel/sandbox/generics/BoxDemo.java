package com.alevel.sandbox.generics;

public class BoxDemo {

    public static void main(String[] args) {
        Cat barsique = new Cat("Барсик");
        barsique.setAge(2);

        Box<Cat> schroedingerBox = new Box<>(barsique);
        schroedingerBox.print();

        Box<Void> empty = new Box<>();
        empty.print();

        CatKillerBox catKillerBox = new CatKillerBox(barsique);
        catKillerBox.randomDeath();
    }

}
