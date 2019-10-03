package com.alevel.sandbox.nullability;

import java.util.Objects;

public final class Rocket {

    private final Warhead warhead;

    private final Coordinates target;

    public Rocket(Warhead warhead, Coordinates target) {
        this.warhead = warhead;
        this.target = target;
    }

    public Warhead getWarhead() {
        return warhead;
    }

    public Coordinates getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return warhead == rocket.warhead &&
                Objects.equals(target, rocket.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warhead, target);
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "warhead=" + warhead +
                ", target=" + target +
                '}';
    }
}
