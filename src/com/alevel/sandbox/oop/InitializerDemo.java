package com.alevel.sandbox.oop;

public class InitializerDemo {

    public static void main(String[] args) {

        System.out.println(Initialized.staticField);

        Initialized object = new Initialized();

        System.out.println(object.field);

        Initialized.staticField = "new static field value";
        object.field = "new field value";

        Initialized newObject = new Initialized();
        System.out.println(Initialized.staticField);
        System.out.println(object.field);
        System.out.println(newObject.field);

    }

}


class Initialized {

    static String staticField;

    static {
        staticField = "static field";
    }

    String field;

    {
        field = "field";
    }

}