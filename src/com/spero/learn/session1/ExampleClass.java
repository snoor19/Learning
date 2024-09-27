package com.spero.learn.session1;

public class ExampleClass {

    private static String staticVar1 = "Static Variable 1";
    private static String staticVar2 = "Static Variable 2";

    private String nonStaticVar;

    public ExampleClass(String nonStaticVar) {
        this.nonStaticVar = nonStaticVar;
    }

    public void demonstrateLocalVariables() {
        String localVar1 = "Local Variable 1";
        int localVar2 = 42;
        double localVar3 = 3.14;
        boolean localVar4 = true;
        char localVar5 = 'A';

        System.out.println("Local Variable 1: " + localVar1);
        System.out.println("Local Variable 2: " + localVar2);
        System.out.println("Local Variable 3: " + localVar3);
        System.out.println("Local Variable 4: " + localVar4);
        System.out.println("Local Variable 5: " + localVar5);
    }

    public static void main(String[] args) {
        ExampleClass example = new ExampleClass("Non-Static Variable");

        example.demonstrateLocalVariables();
    }
}

