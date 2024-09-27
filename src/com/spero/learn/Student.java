package com.spero.learn;

import java.util.ArrayList;

public class Student {

	String name;
	String branch;
	int semistar;
	int marks;


	public Student(String name, String branch, int semistar, int marks) {
		super();
		this.name = name;
		this.branch = branch;
		this.semistar = semistar;
		this.marks = marks;
	}

	public boolean checkValidBranch() {
		if (this.branch.equalsIgnoreCase("CSE") || this.branch.equalsIgnoreCase("EEE")
				|| this.branch.equalsIgnoreCase("IT") || this.branch.equalsIgnoreCase("ECE")) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		ArrayList<Student> students = new ArrayList<>();
		Student noor = new Student("noor", "cse", 1, 70);
		students.add(noor);
		Student irshad = new Student("Irshad", "ece", 2, 75);
		students.add(irshad);
		Student vinay = new Student("vinay", "IT", 1, 80);
		students.add(vinay);
		Student vinayP = new Student("vinayP", "MEC", 2, 75);
		students.add(vinayP);
		
		for (Student student : students) {
			System.out.println(student.name+" is studying valid branch::"+student.checkValidBranch());
		}
		
	}
}
