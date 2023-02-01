package oop.composition;

public class Application {

    public static void main(String[] args) {

        MyPoint p1 = new MyPoint(3, 4);
        MyPoint p2 = new MyPoint(10, 56);
        MyPoint p3 = new MyPoint(7, 8);
        System.out.println(p1.distance(5, 6));
        System.out.println(p1.distance(p2));
        System.out.println(p1.distance());




    }
}