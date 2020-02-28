package com.alevel.sandbox.oop.substance;

public interface Substance {

    double DEFAULT_TEMPERATURE = 20;

    double ABSOLUTE_ZERO = -273.15;

    State heatUp(double t);

    double getTemperature();

    String getName();

}
