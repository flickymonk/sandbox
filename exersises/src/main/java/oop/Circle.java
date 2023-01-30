package oop;

public class Circle {

    private double radius;
    private String color;


    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle() {
        radius = 1;
        color = "red";
    }

    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    public double getArea() {
        return Math.PI*radius*radius;
    }


    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "oop.Circle {" +
                " radius = " + radius +
                ", color = '" + color + '\'' +
                '}';
    }
}
