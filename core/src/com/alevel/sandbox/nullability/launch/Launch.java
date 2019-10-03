package com.alevel.sandbox.nullability.launch;

import com.alevel.sandbox.nullability.Coordinates;
import com.alevel.sandbox.nullability.Rocket;

import java.util.Date;
import java.util.Objects;

public final class Launch {

    private final Coordinates location;

    private final Rocket rocket;

    private final Date date;

    public Launch(Coordinates location, Rocket rocket, Date date) {
        this.location = location;
        this.rocket = rocket;
        this.date = date;
    }

    public Coordinates getLocation() {
        return location;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Launch launch = (Launch) o;
        return Objects.equals(location, launch.location) &&
                Objects.equals(rocket, launch.rocket) &&
                Objects.equals(date, launch.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, rocket, date);
    }

    @Override
    public String toString() {
        return "Launch{" +
                "location=" + location +
                ", rocket=" + rocket +
                ", date=" + date +
                '}';
    }
}
