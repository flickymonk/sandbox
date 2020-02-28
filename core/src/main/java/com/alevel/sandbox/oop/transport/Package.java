package com.alevel.sandbox.oop.transport;

public class Package {

    private final String name;

    private final double volume;

    private final String destination;

    public Package(String name, double volume, String destination) {
        this.name = name;
        this.volume = volume;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    public String getDestination() {
        return destination;
    }
}
