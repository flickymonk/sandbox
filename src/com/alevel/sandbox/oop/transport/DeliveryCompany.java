package com.alevel.sandbox.oop.transport;

public class DeliveryCompany {

    private Transport transport;

    public void deliverPackage(String pkgName, double volume, String destination) {
        Package pkg = new Package(pkgName, volume, destination);
        transport.deliver(pkg);
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
