package com.alevel.sandbox.nullability.storage;

import com.alevel.sandbox.nullability.OptionalRocket;
import com.alevel.sandbox.nullability.Rocket;

public class UndergroundRocketStorage implements RocketStorage {

    private final Rocket[] rockets;

    public UndergroundRocketStorage(Rocket... rockets) {
        this.rockets = rockets;
    }

    @Override
    public Rocket get(int index) throws RocketNotFoundException {
        OptionalRocket rocket = tryToGet(index);
        if (rocket.isPresent()) {
            return rocket.get();
        } else {
            throw new RocketNotFoundException(index);
        }
    }

    @Override
    public OptionalRocket tryToGet(int index) {
        Rocket rocket;
        if (index < 0 || index >= rockets.length) {
            rocket = null;
        } else {
            rocket = rockets[index];
        }
        rockets[index] = null;
        return new OptionalRocket(rocket);
    }
}
