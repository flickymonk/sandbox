package com.alevel.sandbox.generics;

public final class Cat {
    private final String name;
    private int age;
    private boolean alive;

    public Cat(String name) {
        this.name = name;
        age = 0;
        alive = true;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void kill() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", alive=" + alive +
                '}';
    }
}
