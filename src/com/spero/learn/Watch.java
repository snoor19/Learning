package com.spero.learn;
import java.util.Objects;
public class Watch {
	private String brand;
    private String color;
    private double price;
    private String type;
    public Watch(String brand, String color, double price, String type) {
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.type = type;
    }
    public String getBrand() {
        return brand;
    }
    public String getColor() {
        return color;
    }
    public double getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    @Override
    public String toString() {
        return "Watch-->brand='" + brand + ", color='" + color + ", price=" + price +
                ", type='" + type ;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Watch watch = (Watch) o;
        return Double.compare(watch.price, price) == 0 &&
                Objects.equals(brand, watch.brand) &&
                Objects.equals(color, watch.color) &&
                Objects.equals(type, watch.type);
    }
    @Override
    public int hashCode() {
        return Objects.hash(brand, color, price, type);
    }
	public static void main(String[] args) {
		Watch watch1 = new Watch("AmazeFit", "Black", 10000.00, "Smart");
        Watch watch2 = new Watch("AmazeFit", "Black", 10000.00, "Smart");
        System.out.println(watch1.toString());
        if (watch1.equals(watch2)) {
            System.out.println("The two watches are equal.");
        } else {
            System.out.println("The two watches are not equal.");
        }
        System.out.println("HashCode of watch1: " + watch1.hashCode());
        System.out.println("HashCode of watch2: " + watch2.hashCode());
	}
}
