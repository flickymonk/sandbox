package com.alevel.unittestbox.triangle;

import java.util.Objects;

public final class Triangle {

    private final double verticeA;
    private final double verticeB;
    private final double verticeC;

    public Triangle(double verticeA, double verticeB, double verticeC) {

        checkVertices(verticeA, verticeB, verticeC);

        this.verticeA = verticeA;
        this.verticeB = verticeB;
        this.verticeC = verticeC;
    }

    private static void checkVertices(double verticeA, double verticeB, double verticeC) {
        String verticeValues = " A = "
                + verticeA + ", B = " + verticeB + ", C = " + verticeC;

        if (verticeA <= 0) {
            throw new IllegalArgumentException("Vertice A is less or equal to zero!" + verticeValues);
        }
        if (verticeB <= 0) {
            throw new IllegalArgumentException("Vertice B is less or equal to zero!" + verticeValues);
        }
        if (verticeC <= 0) {
            throw new IllegalArgumentException("Vertice C is less or equal to zero!" + verticeValues);
        }
        if (verticeA >= verticeB + verticeC) {
            throw new IllegalArgumentException("Vertice A is greater or equal to the sum of B and C!" + verticeValues);
        }
        if (verticeB >= verticeA + verticeC) {
            throw new IllegalArgumentException("Vertice B is greater or equal to the sum of A and C!" + verticeValues);
        }
        if (verticeC >= verticeA + verticeB) {
            throw new IllegalArgumentException("Vertice C is greater or equal to the sum of A and B!" + verticeValues);
        }
    }

    public double getVerticeA() {
        return verticeA;
    }

    public double getVerticeB() {
        return verticeB;
    }

    public double getVerticeC() {
        return verticeC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.verticeA, verticeA) == 0 &&
                Double.compare(triangle.verticeB, verticeB) == 0 &&
                Double.compare(triangle.verticeC, verticeC) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(verticeA, verticeB, verticeC);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "verticeA=" + verticeA +
                ", verticeB=" + verticeB +
                ", verticeC=" + verticeC +
                '}';
    }
}
