package com.alevel.sandbox.nullability.launch;

import com.alevel.sandbox.nullability.Coordinates;
import com.alevel.sandbox.nullability.Rocket;

import java.util.Date;

public class Submarine implements MobileLaunchPlatform {

    private Coordinates location;

    public Submarine(Coordinates location) {
        this.location = location;
    }

    @Override
    public void move(Coordinates location) {
        System.out.println("Moving from " + this.location + " to " + location);
        this.location = location;
    }

    @Override
    public Launch launch(Rocket rocket) {
        Date date = new Date();

        Launch launch = new Launch(location, rocket, date);

        Coordinates target = rocket.getTarget();

        System.out.println(rocket.getWarhead() +
                " rocket has been launched from the submarine at {" +
                location.getLatitude() + ", " +
                location.getLongitude() + "} at " +
                date + ", targeting coordinates {" +
                target.getLatitude() + ", " +
                target.getLongitude() + "}"
        );

        return launch;
    }
}
