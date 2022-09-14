package com.alevel.sandbox.oop.substance;

abstract class AbstractSubstance implements Substance {

    private double t;

    protected AbstractSubstance() {
        this(DEFAULT_TEMPERATURE);
    }

    protected AbstractSubstance(double t) {
        this.t = t;
    }

    @Override
    public State heatUp(double t) {
        this.t = Math.max(ABSOLUTE_ZERO, this.t + t);
        State state;
        if (this.t > getEvaporationThreshold()) {
            state = State.GAS;
        } else if (this.t > getCrystallizationThreshold()) {
            state = State.LIQUID;
        } else {
            state = State.SOLID;
        }
        return state;
    }

    @Override
    public double getTemperature() {
        return t;
    }

    protected abstract double getCrystallizationThreshold();

    protected abstract double getEvaporationThreshold();

}
