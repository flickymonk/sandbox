package com.alevel.sandbox.collections.practice;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(10);

        tree.add(5);
        tree.add(1);
        tree.add(4);
        tree.add(35);
        tree.add(20);
        tree.add(17);
        tree.add(31);
        tree.add(99);

        System.out.println(tree);

        System.out.println(tree.ascending());
        System.out.println(tree.descending());

        System.out.println(tree.add(10));
        System.out.println(tree.add(4));

        System.out.println(tree.contains(4));

        System.out.println("max: " + tree.max());
        System.out.println("min: " + tree.min());
    }
}
