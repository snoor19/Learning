package com.spero.learn;

public class TestCouch implements Coach {
	@Override
    public void nonStaticMethod() {
        System.out.println("Non-static method implementation.");
        System.out.println("Accessing static variables:");
        System.out.println("STATIC_VAR1: " + STATIC_VAR1);
        System.out.println("STATIC_VAR2: " + STATIC_VAR2);
    }
}
interface Coach {
	static final int STATIC_VAR1 = 100;
    static final String STATIC_VAR2 = "Hello";

    void nonStaticMethod();
}
