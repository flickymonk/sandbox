package oop.composition;

public class Customer {

    private int id;
    private String name;
    private char gender;


    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }
}
