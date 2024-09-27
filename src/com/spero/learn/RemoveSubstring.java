package com.spero.learn;

public class RemoveSubstring {
	public static void main(String[] args) {
		String input = "/server/url/api/adadf/3434/324234";
		boolean con = true;
		while (con) {
			// Find the last index of '/'
			int lastIndex = input.lastIndexOf('/');

			if (lastIndex != -1) {
				// Remove substring from the last index of '/'
				String result = input.substring(0, lastIndex);
				input += ",";
				System.out.println("Original String: " + input);
				System.out.println("Result String: " + result);
				input = result;
			} else {
				System.out.println("Substring not found in the string.");
				con = false;
			}
		}
	}
}
