package com.alevel.sandbox.nullability.launch;

import com.alevel.sandbox.nullability.Coordinates;

public interface MobileLaunchPlatform extends LaunchPlatform {

    void move(Coordinates location);

}
