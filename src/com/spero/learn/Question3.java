package com.spero.learn;

public class Question3 {
	private void hello() {
		System.out.println("Hello Mam");
	}
	private void wish() {
		System.out.println("Good Evening");
	}
	private void add(int x, int y) {
		int total = x+y;
		System.out.println("Total -"+total);
	}
	private void printName(String name) {
		System.out.println("My Self "+name);
	}
	private void printAge(long l ) {
		System.out.println("Age - "+l);
	}
	public static void main(String[] args) {
		Question3 question2 = new Question3();
		question2.hello();
		question2.wish();
		question2.add(10, 20);
		question2.printName("Farjana");
		question2.printAge(22);
	}
}
