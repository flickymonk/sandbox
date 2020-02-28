package com.alevel.sandbox.patterns;

public class SingletonDemo {
    public static void main(String[] args) {
        final Singleton instance1 = Singleton.getInstance();
        final Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
