package com.alevel.sandbox.nullability.storage;

import com.alevel.sandbox.nullability.OptionalRocket;
import com.alevel.sandbox.nullability.Rocket;

public interface RocketStorage {

    Rocket get(int index) throws RocketNotFoundException;

    OptionalRocket tryToGet(int index);

}
