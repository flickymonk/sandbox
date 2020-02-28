package com.alevel.sandbox.nullability;

import java.util.Objects;

public final class Coordinates {

    private final double latitude;

    private final double longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates target = (Coordinates) o;
        return Double.compare(target.latitude, latitude) == 0 &&
                Double.compare(target.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Target{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
