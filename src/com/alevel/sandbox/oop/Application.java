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

        talkToPet(friend);

        Pet enemy = new Cat();
        enemy.setName("Pain");
        enemy.setAge(9);

        talkToPet(enemy);
    }

    public static void talkToPet(Pet pet) {
        pet.say();
    }

}
