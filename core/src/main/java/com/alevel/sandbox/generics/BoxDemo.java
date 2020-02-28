package com.alevel.sandbox.generics;

public class BoxDemo {

    public static void main(String[] args) {
        Cat barsique = new Cat("Барсик");
        barsique.setAge(2);

        Box<Cat> box;
        box = new Box<>(barsique);
        box.print();

        Box<Void> empty = new Box<>();
        empty.print();

        CatKillerBox schrodingerBox = new CatKillerBox(barsique);
        schrodingerBox.randomDeath();
    }

}
