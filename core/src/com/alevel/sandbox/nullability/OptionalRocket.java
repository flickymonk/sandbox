package com.alevel.sandbox.nullability;

import java.util.NoSuchElementException;

public final class OptionalRocket {

    private final Rocket value;

    public OptionalRocket(Rocket value) {
        this.value = value;
    }

    public Rocket get() {
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

}
