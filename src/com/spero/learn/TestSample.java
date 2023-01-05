package com.spero.learn;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSample {

	public static void main(String[] args) {
		
		/*
		 * byte[] timeZone = {-127,00}; String str = new String(timeZone);
		 * System.out.println("Str Data ::"+hexStringToByteArray("8100"));
		 */
		String account_details = "\"{\r\n" + 
				"\"bank_name\":\"Kotak\",\r\n" + 
				"\"user_name\":\"onmobile\",\r\n" + 
				"\"ifsc\":\"KKBK00000\",\r\n" + 
				"\"account_no\":\"123456\",\r\n" + 
				"\"account_type\":\"savings\"\r\n" + 
				"}\"";
		Map<String, String> accountMap = null;
		try {
			account_details = account_details.substring(1,account_details.length()-1);
			accountMap = new ObjectMapper().readValue(account_details, Map.class);
			System.out.println("Data:"+accountMap);
		} catch ( IOException e) {
			System.out.println("In Exception::"+e);
		}
		String result = accountMap!=null?(accountMap.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue())
				.collect(Collectors.joining("|"))):"";
		System.out.println("Result::"+result);
		String str = "/griff/getUserInfo.jsp?sessionid=$REF_ID$";
		str = str.replace("$REF_ID$", "what");
		System.out.println("Next::"+str);
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    byte[] b = new byte[s.length() / 2];
	    for (int i = 0; i < b.length; i++) {
	      int index = i * 2;
	      int v = Integer.parseInt(s.substring(index, index + 2), 16);
	      b[i] = (byte) v;
	    }
	    return b;
	  }
}
