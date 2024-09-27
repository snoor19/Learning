package com.spero.learn.session;

public class Question1 {

    private String brand;
    private String model;
    private int storage;
    private double screenSize;
    private int batteryCapacity;
    private boolean is5GEnabled;

    public Question1(String brand, String model, int storage, double screenSize, int batteryCapacity, boolean is5GEnabled) {
        this.brand = brand;
        this.model = model;
        this.storage = storage;
        this.screenSize = screenSize;
        this.batteryCapacity = batteryCapacity;
        this.is5GEnabled = is5GEnabled;
    }

    public void makeCall(String phoneNumber) {
        System.out.println("Calling " + phoneNumber + "...");
    }

    public void takePicture() {
        System.out.println("Taking a picture...");
    }

    public void displayInfo() {
        System.out.println("Smartphone Information:");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Storage: " + storage + "GB");
        System.out.println("Screen Size: " + screenSize + " inches");
        System.out.println("Battery Capacity: " + batteryCapacity + "mAh");
        System.out.println("5G Enabled: " + (is5GEnabled ? "Yes" : "No"));
    }

    public static void main(String[] args) {
    	Question1 myPhone = new Question1("Apple", "iPhone 13", 256, 6.1, 3095, true);

        System.out.println("Brand: " + myPhone.brand);
        System.out.println("Model: " + myPhone.model);
        System.out.println("Storage: " + myPhone.storage + "GB");
        System.out.println("Screen Size: " + myPhone.screenSize + " inches");
        System.out.println("Battery Capacity: " + myPhone.batteryCapacity + "mAh");
        System.out.println("5G Enabled: " + myPhone.is5GEnabled);

        myPhone.makeCall("123-456-7890");
        myPhone.takePicture();
        myPhone.displayInfo();
    }
}

