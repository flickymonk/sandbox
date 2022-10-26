package com.alevel.sandbox.threads.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCollectionsDemo {


    public static void main(String[] args) {
        List<?> concurrentList = new CopyOnWriteArrayList<>();

        Map<?, ?> concurrentMap = new ConcurrentHashMap<>();
        Set<?> concurrentSet = ConcurrentHashMap.newKeySet();
        // Collections.synchronizedMap(new HashMap<>())

        SortedMap<?, ?> concurrentSortedMap = new ConcurrentSkipListMap<>();

        Queue<?> concurrentQueue = new ConcurrentLinkedQueue<>();
    }

}
