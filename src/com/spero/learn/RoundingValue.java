package com.spero.learn;

public class RoundingValue {

	public static void main(String[] args) {
		System.out.println("RoundingValue::"+getAmtUptoDecimal(12.898989f, 2, "roundoff"));
		double scale = Math.pow(10, 2);
	    System.out.println("Data::"+(float)Math.round(12.2298989f * scale) / scale);
	}
	
	public static String getAmtUptoDecimal(float num, int lenAfterDecimal, String operation) {
		String finalAmt = "";
		int arr[] = {1,2,3,4,5,6,7,8,9};
    	String s= new Float(num).toString();
    	String p=s.substring(s.indexOf('.')+1,s.length());
    	int decimal=Integer.parseInt(p);
    	switch (operation) {
    		case "exact":for(int i : arr) {
			        		if(decimal==i) {
			        			finalAmt = String.format("%.0"+lenAfterDecimal+"f", num);
			        			break;
			        		} else {
			        			double m2 = (int) (num * Math.pow(10, lenAfterDecimal)) / Math.pow(10, lenAfterDecimal) ;
			        	    	finalAmt = String.format("%.0"+lenAfterDecimal+"f", m2);
			        	    	break;
			        		}
        				}
    					break;
    		case "roundoff":finalAmt = String.format("%.0"+lenAfterDecimal+"f", num);
    						break;
    		default: System.out.println("Operation must be exact or roundoff. Returning amount as it is");
    				finalAmt = s;
    				break;
    	}    	
		return finalAmt;
	}

}
