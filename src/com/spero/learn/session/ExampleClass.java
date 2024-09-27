package com.spero.learn.session;

public class ExampleClass {

    private String name;
    private int age;
    private double salary;

    private static String company = "Tech Solutions";

    public ExampleClass(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: $" + salary);
    }

    public void increaseSalary(double increment) {
        salary += increment;
        System.out.println("New Salary: $" + salary);
    }

    public static void displayCompany() {
        System.out.println("Company: " + company);
    }

    public static void main(String[] args) {
        ExampleClass employee = new ExampleClass("John Doe", 30, 50000.00);

        employee.displayDetails();
        employee.increaseSalary(5000);

        ExampleClass.displayCompany();
    }
}
