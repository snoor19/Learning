package com.spero.learn;

public class CochingDriver {
	public static void main(String[] args) {
		Qspiders qspiders = new Qspiders("TestYantra-Qspider", "Bangalore", 200, "Qspiders", 5000);
		qspiders.qspDetails();
		Jspiders jspiders = new Jspiders("Jspiders", "BasavanaGudi", 100, 20);
		jspiders.jspDetails();
		Pyspiders pyspiders = new Pyspiders("PySpiders", "BTM", 50, "Python", "Yearly");
		pyspiders.pyspDetails();
	}
}
class TestYantra {
	String name;
	String location;
	int noOfEmployees;
	public TestYantra(String name, String location, int noOfEmployees) {
		super();
		this.name = name;
		this.location = location;
		this.noOfEmployees = noOfEmployees;
	}
}
class Qspiders extends TestYantra {
	String branch;
	int noOfStudents;
	public Qspiders(String name, String location, int noOfEmployees, String branch, int noOfStudents) {
		super(name, location, noOfEmployees);
		this.branch = branch;
		this.noOfStudents = noOfStudents;
	}
	
	public void qspDetails() {
		System.out.println("Name::"+name);
		System.out.println("Location::"+location);
		System.out.println("No Of Emloyees::"+noOfEmployees);
		System.out.println("Branch::"+branch);
		System.out.println("No of Students::"+noOfStudents);
	}
}
class Jspiders extends TestYantra {
	int noOfTrainers;

	public Jspiders(String name, String location, int noOfEmployees, int noOfTrainers) {
		super(name, location, noOfEmployees);
		this.noOfTrainers = noOfTrainers;
	}
	public void jspDetails() {
		System.out.println("Name::"+name);
		System.out.println("Location::"+location);
		System.out.println("No Of Emloyees::"+noOfEmployees);
		System.out.println("No of Trainers::"+noOfTrainers);
	}
}
class Pyspiders extends TestYantra {
	String coursesOffered;
	String feesStructure;
	
	public Pyspiders(String name, String location, int noOfEmployees, String coursesOffered, String feesStructure) {
		super(name, location, noOfEmployees);
		this.coursesOffered = coursesOffered;
		this.feesStructure = feesStructure;
	}
	public void pyspDetails() {
		System.out.println("Name::"+name);
		System.out.println("Location::"+location);
		System.out.println("No Of Emloyees::"+noOfEmployees);
		System.out.println("Course Offered::"+coursesOffered);
		System.out.println("Fees Structured::"+feesStructure);
	}
}