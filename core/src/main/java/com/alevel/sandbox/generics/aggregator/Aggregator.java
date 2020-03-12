package com.alevel.sandbox.generics.aggregator;

public interface Aggregator<A, T> {

    A aggregate(T[] items);

}
