package com.alevel.sandbox.generics.aggregator;

public class CountAggregator<T> implements Aggregator<Integer, T> {
    @Override
    public Integer aggregate(T[] items) {
        return items == null ? 0 : items.length;
    }
}
