package oop.classes;

import oop.classes.Ball;

public class Application {

    public static void main(String[] args) {

        //Exercise 1.9

        Ball ball = new Ball(1.1f, 2.2f, 10, 3.3f, 4.4f);
        System.out.println(ball);  // toString()

        // Test Setters and Getters
        ball.setX(80.0f);
        ball.setY(35.0f);
        ball.setRadius(5);
        ball.setxDelta(4.0f);
        ball.setyDelta(6.0f);
        System.out.println(ball);  // toString()
        System.out.println("x is: " + ball.getX());
        System.out.println("y is: " + ball.getY());
        System.out.println("radius is: " + ball.getRadius());
        System.out.println("xDelta is: " + ball.getxDelta());
        System.out.println("yDelta is: " + ball.getyDelta());

        float xMin = 0.0f;
        float xMax = 100.0f;
        float yMin = 0.0f;
        float yMax = 50.0f;
        for (int i = 0; i < 15; i++) {
            ball.move();
            System.out.println(ball);
            float xNew = ball.getX();
            float yNew = ball.getY();
            int radius = ball.getRadius();
            // Check boundary value to bounce back
            if ((xNew + radius) > xMax || (xNew - radius) < xMin) {
                ball.reflectHorizontal();
            }
            if ((yNew + radius) > yMax || (yNew - radius) < yMin) {
                ball.reflectVertical();
            }
        }
    }

    //Exercise 1.8
    // Test constructors and toString()
//        Time t1 = new Time(1, 2, 3);
//        System.out.println(t1);  // toString()
//
//        // Test Setters and Getters
//        t1.setHour(4);
//        t1.setMinute(5);
//        t1.setSecond(6);
//        System.out.println(t1);  // toString()
//        System.out.println("Hour: " + t1.getHour());
//        System.out.println("Minute: " + t1.getMinute());
//        System.out.println("Second: " + t1.getSecond());
//
//        // Test setTime()
//        t1.setTime(23, 59, 58);
//        System.out.println(t1);  // toString()
//
//        // Test nextSecond();
//        System.out.println(t1.nextSecond());
//        System.out.println(t1.nextSecond().nextSecond());

    // Test previousSecond()
//        System.out.println(t1.previousSecond());
//        System.out.println(t1.previousSecond().previousSecond());


    //Exercise 1.7
//        Date date = new Date(1, 1, 1993);
//        System.out.println(date);


    //Exercise 1.6
//        Account account1 = new Account("1", "Adam");
//        Account account2 = new Account("2", "Last", 150);
//        System.out.println(account1.toString());
//        System.out.println(account2.toString());
//        account2.transferTo(account1, 100);
//        System.out.println(account1.toString());
//        System.out.println(account2.toString());

    //Exercise 1.5
//        InvoiceTeam team = new InvoiceTeam("1", "asdasd", 2, 12.3);
//        System.out.println(team.toString());


    //Exercise 1.4
//        Employee employee = new Employee(1, "Adam", "Last", 1200);
//        Employee employee1 = new Employee(2, "Last", "Adam", 1500);
//
//        System.out.println(employee.toString());
//        System.out.println(employee1.toString());
//
//        System.out.println(employee.getAnnualSalary());
//        System.out.println(employee.raiseSalary(10));


}