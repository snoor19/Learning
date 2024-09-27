package com.spero.learn.session1;

public class Question3 {

    public void printMessage() {
        System.out.println("This is a no-argument method.");
    }

    public void displayGreeting() {
        System.out.println("Hello, welcome to SampleClass!");
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public String concatenate(String str1, String str2) {
        return str1 + str2;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
    	Question3 sample = new Question3();

        sample.printMessage();
        sample.displayGreeting();

        int sum = sample.add(10, 5);
        System.out.println("Sum: " + sum);

        int difference = sample.subtract(10, 5);
        System.out.println("Difference: " + difference);

        String concatenatedString = sample.concatenate("Hello, ", "World!");
        System.out.println("Concatenated String: " + concatenatedString);

        int product = sample.multiply(10, 5);
        System.out.println("Product: " + product);
    }
}

