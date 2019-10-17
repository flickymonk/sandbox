package com.alevel.sandbox.generics;

public class PairDemo {
    public static void main(String[] args) {
        Pair<Integer, String> isp = new Pair<>(0, "zero");
        Pair<String, Integer> swapped = Pair.swap(isp);
        System.out.println(swapped);
    }
}
