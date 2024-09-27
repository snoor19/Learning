package com.spero.learn.session1;

public class Question2 {

    public void printGreeting() {
        System.out.println("Hello, welcome to Question2!");
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            System.out.println("Division by zero is not allowed.");
            return 0;
        }
        return (double) a / b;
    }

    public static void main(String[] args) {
    	Question2 example = new Question2();

        example.printGreeting();
        
        int sum = example.add(10, 5);
        System.out.println("Sum: " + sum);
        
        int difference = example.subtract(10, 5);
        System.out.println("Difference: " + difference);
        
    }
     
}
