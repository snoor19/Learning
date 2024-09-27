package com.spero.learn;

public class AbstractClassExample {
	public static void main(String[] args) {
        Dog myDog = new Dog("Lucy", 3);
        myDog.displayDetails();
        myDog.sound();
    }
}
abstract class Animal {
    String name;
    int age;
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    abstract void sound();
    public void displayDetails() {
        System.out.println("Animal Name: " + name);
        System.out.println("Animal Age: " + age);
    }
}
class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }
    @Override
    void sound() {
        System.out.println(name + " says: Bow Bow!");
    }
}
