package com.alevel.sandbox.oop.transport;

public class Main {

    public static void main(String[] args) {
        DeliveryCompany deliveryCompany = new DeliveryCompany();
        deliveryCompany.setTransport(new Train());

        deliveryCompany.deliverPackage("xps13", 10.4, "Kharkiv");

        deliveryCompany.setTransport(new Ship());
        deliveryCompany.deliverPackage("fridge", 1000, "Kharkiv");
    }
}
