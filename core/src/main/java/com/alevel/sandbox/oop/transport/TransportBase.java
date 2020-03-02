package com.alevel.sandbox.oop.transport;

public abstract class TransportBase implements Transport {

    @Override
    public void deliver(Package pkg) {
        System.out.println("Package " + pkg.getName() +
                " with volume = " + pkg.getVolume() +
                " was delivered to " + pkg.getDestination() +
                " by " + getTransportName());
    }

    protected abstract String getTransportName();

}
