package com.spero.learn;

import java.util.HashMap;
import java.util.Map;

public class Maplooper {
	
	private static Map<String, String> attributesData = new HashMap<String, String>();

	public static void main(String[] args) {
		
		attributesData.put("DISPATCH_TIME", "TIME");
    	
    	String attributes = null;
    	
    	 for (Map.Entry<String,String> entry : attributesData.entrySet()) {
    		 if( attributes != null )
    			 attributes = attributes +"|"+ entry.getKey()+":"+entry.getValue();
    		 else
    			 attributes = entry.getKey()+":"+entry.getValue();
    	 }
    	 
    	 System.out.println("Data ::"+attributes);
	}

}
