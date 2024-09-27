package com.spero.learn.session;

public class SampleClass {

    private String name;
    private int age;
    private double salary;

    private static String companyName = "Tech Solutions";
    private static String companyAddress = "1234 Tech Avenue";
    private static int companyEstablishedYear = 2000;
    private static int numberOfEmployees;

    public SampleClass(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void displayEmployeeDetails() {
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Age: " + age);
        System.out.println("Employee Salary: $" + salary);
    }

    public static void displayCompanyDetails() {
        System.out.println("Company Name: " + companyName);
        System.out.println("Company Address: " + companyAddress);
        System.out.println("Established Year: " + companyEstablishedYear);
        System.out.println("Number of Employees: " + numberOfEmployees);
    }

    public static void setNumberOfEmployees(int num) {
        numberOfEmployees = num;
    }

    public static void main(String[] args) {
        SampleClass employee = new SampleClass("Alice Johnson", 28, 75000.00);

        employee.displayEmployeeDetails();

        SampleClass.setNumberOfEmployees(150);
        SampleClass.displayCompanyDetails();
    }
}

