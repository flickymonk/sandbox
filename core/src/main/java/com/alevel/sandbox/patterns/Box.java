package com.alevel.sandbox.patterns;

import java.util.Objects;

final class Box implements Cloneable {

    private final int height;

    private final int width;

    private final int depth;

    private Box(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public static Box create(int height, int width, int depth) {
        if (height <= 0 || width <= 0 || depth <= 0) {
            return null;
        } else {
            return new Box(height, width, depth);
        }
    }

    public static Box create(int side) {
        return create(side, side, side);
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

    @Override
    public Box clone() {
        try {
            return (Box) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String toString() {
        return "Box{" +
                "height=" + height +
                ", width=" + width +
                ", depth=" + depth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return height == box.height &&
                width == box.width &&
                depth == box.depth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, depth);
    }

    static class BoxBuilder {

        private int height;

        private int width;

        private int depth;

        BoxBuilder height(int height) {
            this.height = height;
            return this;
        }

        BoxBuilder width(int width) {
            this.width = width;
            return this;
        }

        BoxBuilder depth(int depth) {
            this.depth = depth;
            return this;
        }

        Box build() {
            return Box.create(height, width, depth);
        }

    }
}
