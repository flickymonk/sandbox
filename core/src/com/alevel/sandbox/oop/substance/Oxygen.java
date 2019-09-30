package com.alevel.sandbox.oop.substance;

public class Oxygen extends AbstractSubstance {

    private static final String NAME = "Oxygen";

    private static final double CRYSTALLIZATION_THRESHOLD = -218.8;

    private static final double EVAPORATION_THRESHOLD = -182.98;

    public Oxygen() {
        super();
    }

    public Oxygen(double t) {
        super(t);
    }

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
