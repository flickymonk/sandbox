package com.alevel.unittestbox.triangle;

import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void test_canCreateTriangle() {
        double verticeA = 10;
        double verticeB = 12;
        double verticeC = 13.5;
        Triangle t = new Triangle(verticeA, verticeB, verticeC);

        assertEquals(verticeA, t.getVerticeA(), 0);
        assertEquals(verticeB, t.getVerticeB(), 0);
        assertEquals(verticeC, t.getVerticeC(), 0);
    }

    @Test
    public void test_sumOfTwoVertices_ShouldBeGreaterThanThird() {
        assertInvalidTriangle(10, 5, 5,
                "Vertice A is greater or equal to the sum of B and C! A = 10.0, B = 5.0, C = 5.0");
        assertInvalidTriangle(5, 10, 5,
                "Vertice B is greater or equal to the sum of A and C! A = 5.0, B = 10.0, C = 5.0");
        assertInvalidTriangle(5, 5, 10,
                "Vertice C is greater or equal to the sum of A and B! A = 5.0, B = 5.0, C = 10.0");


        assertInvalidTriangle(100, 5, 5,
                "Vertice A is greater or equal to the sum of B and C! A = 100.0, B = 5.0, C = 5.0");

        assertInvalidTriangle(50, 5.5, 5,
                "Vertice A is greater or equal to the sum of B and C! A = 50.0, B = 5.5, C = 5.0");
    }

    @Test
    public void test_allVerticesShouldBeGreaterThanZero() {
        assertInvalidTriangle(0, 1, 1,
                "Vertice A is less or equal to zero! A = 0.0, B = 1.0, C = 1.0");
        assertInvalidTriangle(-10, 1, 1,
                "Vertice A is less or equal to zero! A = -10.0, B = 1.0, C = 1.0");
        assertInvalidTriangle(-10, 0, 1,
                "Vertice A is less or equal to zero! A = -10.0, B = 0.0, C = 1.0");
        assertInvalidTriangle(1, 0, 1,
                "Vertice B is less or equal to zero! A = 1.0, B = 0.0, C = 1.0");
        assertInvalidTriangle(1, -10, 1,
                "Vertice B is less or equal to zero! A = 1.0, B = -10.0, C = 1.0");
        assertInvalidTriangle(1, -10, 0,
                "Vertice B is less or equal to zero! A = 1.0, B = -10.0, C = 0.0");
        assertInvalidTriangle(1, 1, 0,
                "Vertice C is less or equal to zero! A = 1.0, B = 1.0, C = 0.0");
        assertInvalidTriangle(1, 1, -10,
                "Vertice C is less or equal to zero! A = 1.0, B = 1.0, C = -10.0");
    }

    private static void assertInvalidTriangle(double verticeA, double verticeB, double verticeC, String expectedErrorMessage) {
        try {
            Triangle t = new Triangle(verticeA, verticeB, verticeC);
            fail("Managed to create invalid triangle: " + t);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }
}
