package com.alevel.sandbox.oop;

public class Box {

    private int heigth;

    private int width;

    private int depth;

    public Box() {}

    public Box(int heigth, int width, int depth) {
        this.heigth = heigth;
        this.width = width;
        this.depth = depth;
    }

    public int getHeigth() {
        return heigth;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public Box withHeight(int height) {
        this.heigth = height;
        return this;
    }

    public Box withWidth(int width) {
        this.width = width;
        return this;
    }

    public Box withDepth(int depth) {
        this.depth = depth;
        return this;
    }
}
