package com.alevel.sandbox.oop.substance;

public class Iron extends AbstractSubstance {

    private static final String NAME = "Iron";

    private static final double CRYSTALLIZATION_THRESHOLD = 1538;

    private static final double EVAPORATION_THRESHOLD = 2862;

    @Override
    protected double getCrystallizationThreshold() {
        return CRYSTALLIZATION_THRESHOLD;
    }

    @Override
    protected double getEvaporationThreshold() {
        return EVAPORATION_THRESHOLD;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
