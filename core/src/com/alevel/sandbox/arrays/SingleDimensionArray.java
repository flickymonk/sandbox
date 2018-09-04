package com.alevel.sandbox.arrays;

public class SingleDimensionArray {

    public static void main(String[] args) {

        int[] a = new int[10]; //index 0-9

        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        System.out.println("a[7] = " + a[7]);

//        System.out.println(a[10]); //index out of bounds

        //initializer

        String[] s = {"one", "two", "three"};
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }

    }

}
