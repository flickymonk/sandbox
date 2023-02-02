package oop.composition;

public class Circle {

    private MyPoint center;
    private int radius;

    public Circle() {
    }

    public Circle(MyPoint center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(int x, int y, int radius) {
        center = new MyPoint(x, y);
        this.radius = radius;
    }

    public MyPoint getCenter() {
        return center;
    }

    public void setCenter(MyPoint center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }




}
