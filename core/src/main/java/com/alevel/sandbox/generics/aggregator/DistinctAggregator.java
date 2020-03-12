package com.alevel.sandbox.generics.aggregator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DistinctAggregator<T> implements Aggregator<Integer, T> {

    @Override
    public Integer aggregate(T[] items) {
        Set<T> distinct = new HashSet<>();
        Collections.addAll(distinct, items);
        return distinct.size();
    }

}
