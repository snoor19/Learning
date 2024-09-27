package com.spero.learn;

public class RegixCheck {

	public static void main(String[] args) {
		String value = "1";
		if (value != null && !value.isEmpty() && value.matches("^[a-zA-Z0-9]*$")) {
			System.out.println("ALLOWED");
		} else {
			System.out.println("NOT_ALLOWED");
		}
	}

}
