package com.alevel.sandbox.generics.aggregator;

import java.util.StringJoiner;

public class CSVAggregator implements Aggregator<String, Object> {
    @Override
    public String aggregate(Object[] items) {
        if (items == null) return "";
        var joiner = new StringJoiner(",");
        for (Object item : items) {
            joiner.add(String.valueOf(item));
        }
        return joiner.toString();
    }
}
