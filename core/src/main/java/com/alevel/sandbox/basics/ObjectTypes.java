package com.alevel.sandbox.basics;

public class ObjectTypes {

    public static void main(String[] args) {

        Box mybox = new Box("My Box");

        System.out.println(mybox.getLabel());

        System.out.println("Number of boxes = " + Box.getNumberOfBoxes());

//         Box.getNumberOfBoxes() == mybox.getNumberOfBoxes();
    }

}

class Box {

    private static int numberOfBoxes = 0;

    private String label;

    //constructor
    Box(String label) {
        this.label = label;

        numberOfBoxes++;
    }

    static int getNumberOfBoxes() {
        return numberOfBoxes;
    }

    String getLabel() {
        return label;
    }
}
