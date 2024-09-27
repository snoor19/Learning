package com.spero.learn;

public class CornerHouse {
	String branch;
	int noOfTopping;
	double price;
	int noOfFlavours;
	public CornerHouse(String branch, int noOfTopping, double price, int noOfFlavours) {
		this.branch = branch;
		this.noOfTopping = noOfTopping;
		this.price = price;
		this.noOfFlavours = noOfFlavours;
	}
	public CornerHouse(int noOfFlavours) {
		this.noOfFlavours = noOfFlavours;
		
	}
	public CornerHouse(String branch, int noOfTopping, double price) {
		super();
		this.branch = branch;
		this.noOfTopping = noOfTopping;
		this.price = price;
	}
	public CornerHouse(String branch, int noOfTopping) {
		super();
		this.branch = branch;
		this.noOfTopping = noOfTopping;
	}
	
	
	
	
}
