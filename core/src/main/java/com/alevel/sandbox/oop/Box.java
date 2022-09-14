package com.alevel.sandbox.oop;

import java.util.Objects;

public class Box {

    private int height;

    private int width;

    private int depth;

    public Box() {}

    public Box(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public Box withHeight(int height) {
        this.height = height;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return height == box.height && width == box.width && depth == box.depth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, depth);
    }

    @Override
    public String toString() {
        return "Box{" +
                "height=" + height +
                ", width=" + width +
                ", depth=" + depth +
                '}';
    }
}
