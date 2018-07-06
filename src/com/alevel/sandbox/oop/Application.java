package com.alevel.sandbox.oop;

public class Application {

    public static void main(String[] args) {
        //lets make a dog
        Pet friend = new Dog();

        friend.setName("Дружок");
        friend.setAge(2);

        String name = friend.getName();
        int age = friend.getAge();

        System.out.println(name + " is " + age + " years old");

        friend.say();

        Pet enemy = new Cat();
        enemy.setName("Pain");
        enemy.setAge(9);
        enemy.say();

        int howCute = ((Cat) enemy).howCute();
        System.out.println(howCute);

        Cute cutie = new Cat();
        System.out.println(cutie.howCute());
    }

}
