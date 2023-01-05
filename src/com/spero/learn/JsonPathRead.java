package com.spero.learn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import com.jayway.jsonpath.JsonPath;

public class JsonPathRead {

	public static void main(String[] args) {
		Object value="";
		try{
			value=JsonPath.read("{\r\n" + 
					"    \"responseCode\": \"0\",\r\n" + 
					"    \"responseDesc\": \"SUCCESS\",\r\n" + 
					"    \"count\": 1,\r\n" + 
					"    \"result\": [\r\n" + 
					"        {\r\n" + 
					"			 \"accountId\": 722344213,\r\n" + 
					"            \"customerId\": 33176932,\r\n" + 
					"			\"productGroup\": \"GS\"\r\n" + 
					"		}\r\n" + 
					"	]\r\n" + 
					"}","$.result[:1].productGroup"); //cannot be string as for integers it throws exception
			FileReader fileReader = new FileReader(new File("D:\\Learn\\PoCs\\Learning\\Learning\\src\\com\\spero\\learn\\regex.txt"));
			BufferedReader bReader = new BufferedReader(fileReader);
			String subRecord = null;
			while ((subRecord = bReader.readLine()) != null) {
				System.out.println("Val::"+subRecord);
				value =  value.toString().replaceAll(subRecord, "");
				System.out.println("Value to be returned::"+value.toString());
			}
		}catch(Exception e){
			System.out.println("Exception in fetching value from JSON::"+e.getMessage());
		}
		
	}

}
