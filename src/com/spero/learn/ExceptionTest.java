package com.spero.learn;

public class ExceptionTest {

	public static void main(String[] args) {
		try {
			System.out.println("Hello-1-Try");
			try {
				System.out.println("Hello-2-Try");
				String str = new String();
				System.out.println(str.charAt(3));
			} catch (Exception e) {
				System.out.println("Hello Inside cath");
				Thread.currentThread().interrupt();
				throw e;
			} finally {
				System.out.println("Hello-finally");
				throw new Exception();
			}
		}catch (Exception e) {
			System.out.println("Hello Catch"+e);
		}
		System.out.println("After Test Log");
		
	}

}
