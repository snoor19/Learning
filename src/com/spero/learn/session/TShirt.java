package com.spero.learn.session;

public class TShirt {

     String brand;
     String size;
     String color;
     double price;
     String material;
     boolean isPrinted;
     String gender;

    public TShirt(String brand, String size, String color, double price, String material, boolean isPrinted, String gender) {
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.price = price;
        this.material = material;
        this.isPrinted = isPrinted;
        this.gender = gender;
    }

    

    public static void main(String[] args) {
        TShirt myTShirt = new TShirt("Nike", "M", "Blue", 19.99, "Cotton", true, "Unisex");

        System.out.println("Brand: " + myTShirt.brand);
        System.out.println("Size: " + myTShirt.size);
        System.out.println("Color: " + myTShirt.color);
        System.out.println("Price: $" + myTShirt.price);
        System.out.println("Material: " + myTShirt.material);
        System.out.println("Is Printed: " + myTShirt.isPrinted);
        System.out.println("Gender: " + myTShirt.gender);
        
    }
}

