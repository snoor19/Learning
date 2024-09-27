package com.spero.learn;

public class MyClass implements MyInterface1{
	@Override
    public void myMethod() {
        System.out.println("Non-static method implementation in MyClass.");
    }
	public static void main(String[] args) {
		System.out.println("Accessing staticVar1: " + MyInterface1.staticVar1);
        System.out.println("Accessing staticVar2: " + MyInterface1.staticVar2);
        MyInterface1.init();
        MyClass myClassInstance = new MyClass();
        myClassInstance.myMethod();
	}
}
interface MyInterface1 {
    static int staticVar1 = 10;
    static String staticVar2 = "Hello";
    
    static void init() {
        System.out.println("Static method in MyInterface called");
        System.out.println("Static variables: staticVar1 = " + staticVar1 + ", staticVar2 = " + staticVar2);
    }
    void myMethod();
}
