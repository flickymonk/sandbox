package com.alevel.sandbox.generics;

public class AvgAggregator implements Aggregator<Double, Number> {
    @Override
    public Double aggregate(Number[] items) {
        int length = items.length;
        if (length == 0) {
            throw new IllegalArgumentException("Empty array has no average value");
        }
        double sum = 0;
        for (Number item : items) {
            if (item != null) {
                sum += item.doubleValue();
            }
        }
        return sum / length;
    }
}
