package com.alevel.sandbox.generics;

abstract class SumAggregator<T extends Number> implements Aggregator<T, T> {

    @Override
    public T aggregate(T[] items) {
        if (items == null) {
            return null;
        }
        int length = items.length;
        if (length == 0) {
            throw new IllegalArgumentException("Empty array has no sum");
        }
        T sum = items[0];
        for (int i = 1; i < length; i++) {
            sum = sum(sum, items[i]);
        }
        return sum;
    }

    abstract T sum(T x, T y);

}
