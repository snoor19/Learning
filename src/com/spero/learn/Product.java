package com.spero.learn;

public class Product {
	String productName;
	String productId;
	char productCatgeory;
	int productPrice;
	
	
	public Product(String productName, String productId, char productCatgeory, int productPrice) throws Exception {
		super();
		this.productName = productName;
		this.productId = productId;
		if(productCatgeory == 'E' || productCatgeory == 'C'||productCatgeory == 'H'||productCatgeory == 'T')
			this.productCatgeory = productCatgeory;
		else
			throw new Exception("Invalid productCatgeory");
		this.productPrice = productPrice;
	}
	
	private String getProducCatName() {
		if(this.productCatgeory=='E')
			return "Electronics";
		else if(this.productCatgeory=='C')
			return "Cosmotics";
		else if(this.productCatgeory=='H')
			return "Home Applience";
		else if(this.productCatgeory=='T')
			return "Toys";
		else
			return null;
	}


	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productId=" + productId + ", productCatgeory="
				+ getProducCatName() + ", productPrice=" + productPrice + "]";
	}
	
	public static void main(String[] args) {
		try {
			Product product = new Product("Mobile", "adsafdsdf", 'S', 1000);
			System.out.println("Produce Details");
			System.out.println("--------------------");
			System.out.println(product.toString());
		} catch (Exception e) {
			System.err.println("Exception::"+e);
		}
	}
	
}
