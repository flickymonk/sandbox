package com.alevel.sandbox.oop.transport;

public final class Train implements Transport {

    @Override
    public void deliver(Package pkg) {
        System.out.println("Package " + pkg.getName() +
                " with volume = " + pkg.getVolume() +
                " was delivered to " + pkg.getDestination() +
                " by train");
    }

}
