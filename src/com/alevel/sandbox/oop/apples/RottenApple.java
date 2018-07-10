package com.alevel.sandbox.oop.apples;

public class RottenApple extends Apple {

    private int wormCount;

    public RottenApple(int wormCount) {
        this.wormCount = wormCount;
    }

    public int getWormCount() {
        return wormCount;
    }

    public void setWormCount(int wormCount) {
        this.wormCount = wormCount;
    }
}
