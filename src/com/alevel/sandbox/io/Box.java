package com.alevel.sandbox.io;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

final class Box implements Serializable {

    private static final long serialVersionUID = -1L;

    private double length;
    private double width;
    private double depth;

    private transient String[] metadata;

    public Box() {
    }

    public Box(double length, double width, double depth, String... metadata) {
        this.length = length;
        this.width = width;
        this.depth = depth;
        this.metadata = metadata;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String[] getMetadata() {
        return metadata;
    }

    public void setMetadata(String[] metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Box{" +
                "length=" + length +
                ", width=" + width +
                ", depth=" + depth +
                ", metadata=" + Arrays.toString(metadata) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Double.compare(box.length, length) == 0 &&
                Double.compare(box.width, width) == 0 &&
                Double.compare(box.depth, depth) == 0 &&
                Arrays.equals(metadata, box.metadata);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(length, width, depth);
        result = 31 * result + Arrays.hashCode(metadata);
        return result;
    }
}
