package com.spero.learn;

public class ExceptionExample {

	public static void main(String[] args) {
		try {
			int i = 1/0;
			System.out.println("i->"+i);
		} catch (Exception e) {
			System.err.println("Exception::"+e);
		}
		System.out.println("Hello1");
		System.out.println("Hello2");
		
	}

}
