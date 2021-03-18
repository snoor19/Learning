package com.spero.learn;

import java.net.URLDecoder;

public class EncryptDepricate {
	public static void main(String[] args) {
	String decparams = null;
	String msisdn = "7795349007";
	try {
//		System.out.println("Old decode::"+URLDecoder.decode(msisdn));
		decparams = URLDecoder.decode(msisdn,java.nio.charset.StandardCharsets.UTF_8.toString());
		System.out.println("decParams with new ::"+decparams);
		String map = URLDecoder.decode(decparams,java.nio.charset.StandardCharsets.UTF_8.toString());
	} catch (Exception e) {
		System.out.println("Exception::"+e);
	}
	
	}
}
