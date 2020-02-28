package com.alevel.sandbox.collections;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ComparatorDemo {
    public static void main(String[] args) {
        SortedSet<String> lexisort = new TreeSet<>(Comparator.reverseOrder());
        lexisort.add("ant");
        lexisort.add("maven");
        lexisort.add("gradle");
        System.out.println(lexisort);

        StringLengthComparator stringLengthComparator = new StringLengthComparator();
        SortedSet<String> lengthSort = new TreeSet<>(stringLengthComparator);
        lengthSort.addAll(lexisort);
        System.out.println(lengthSort);

        SortedSet<String> reverseLengthSort = new TreeSet<>(stringLengthComparator.reversed());
        reverseLengthSort.addAll(lexisort);
        System.out.println(reverseLengthSort);
    }
}
