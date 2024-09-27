package com.spero.learn;

public class StringSplitArray {

	public static void main(String[] args) {
		String str = "http://localhost:8080/VC-11450/redirect.jsp?";
		String[] arr=str.split("\\?");
		System.out.println("Length:"+arr.length);
	}
}
