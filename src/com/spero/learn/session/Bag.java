package com.spero.learn.session;

public class Bag {

    private String brand;
    private String color;
    private double capacity;
    private double price;

    private static String material = "Nylon";
    private static String countryOfOrigin = "China";
    private static int totalBagsProduced;
    private static double totalRevenue;

    public Bag(String brand, String color, double capacity, double price) {
        this.brand = brand;
        this.color = color;
        this.capacity = capacity;
        this.price = price;
        totalBagsProduced++;
        totalRevenue += price;
    }

    public void displayBagDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Color: " + color);
        System.out.println("Capacity: " + capacity + " liters");
        System.out.println("Price: $" + price);
    }

    public static void displayBagStatistics() {
        System.out.println("Material: " + material);
        System.out.println("Country of Origin: " + countryOfOrigin);
        System.out.println("Total Bags Produced: " + totalBagsProduced);
        System.out.println("Total Revenue: $" + totalRevenue);
    }

    public static void setMaterial(String newMaterial) {
        material = newMaterial;
    }

    public static void main(String[] args) {
        Bag bag1 = new Bag("Nike", "Black", 25.0, 79.99);
        Bag bag2 = new Bag("Adidas", "Blue", 30.0, 89.99);

        bag1.displayBagDetails();
        System.out.println();
        bag2.displayBagDetails();
        System.out.println();

        Bag.displayBagStatistics();
        Bag.setMaterial("Polyester");
        System.out.println("\nAfter changing material:");
        Bag.displayBagStatistics();
    }
}

