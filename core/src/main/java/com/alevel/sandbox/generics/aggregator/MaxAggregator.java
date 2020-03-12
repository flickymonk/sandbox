package com.alevel.sandbox.generics.aggregator;

public class MaxAggregator<T extends Comparable<? super T>> implements Aggregator<T, T> {
    @Override
    public T aggregate(T[] items) {
        if (items == null) {
            return null;
        }
        int length = items.length;
        if (length == 0) {
            throw new IllegalArgumentException("Empty array has no max element");
        }
        T max = items[0];
        for (int i = 1; i < length; i++) {
            T next = items[i];
            if (next != null && (max == null || max.compareTo(next) < 0)) {
                max = next;
            }
        }
        return max;
    }
}
