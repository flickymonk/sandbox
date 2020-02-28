package com.alevel.sandbox.generics;

public interface Aggregator<A, T> {

    A aggregate(T[] items);

}
