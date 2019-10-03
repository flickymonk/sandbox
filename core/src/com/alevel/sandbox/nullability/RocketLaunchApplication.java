package com.alevel.sandbox.nullability;

import com.alevel.sandbox.nullability.launch.Launch;
import com.alevel.sandbox.nullability.launch.LaunchPlatform;
import com.alevel.sandbox.nullability.launch.Silo;
import com.alevel.sandbox.nullability.storage.RocketStorage;
import com.alevel.sandbox.nullability.storage.UndergroundRocketStorage;

public class RocketLaunchApplication {
    public static void main(String[] args) {

        Coordinates bellfast = new Coordinates(-5.93936, 54.66682);

        Rocket bellfastNuke = new Rocket(Warhead.NUCLEAR, bellfast);

        RocketStorage storage = new UndergroundRocketStorage(bellfastNuke);

        LaunchPlatform site000 = new Silo(new Coordinates(0.0, 0.0));

        OptionalRocket r0 = storage.tryToGet(0);

        if (r0.isPresent()) {
            Rocket rocket = r0.get();
            Launch launch = site000.launch(rocket);
            System.out.println("Verifying launch: " + launch);
        }

    }
}
