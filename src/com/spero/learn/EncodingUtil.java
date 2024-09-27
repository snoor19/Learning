package com.spero.learn;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;

public class EncodingUtil {

	
	private EncodingUtil(){
		//this is a util class 
	}
	
	public static String encodeBase64String(byte[] bytes){
		return Base64.encodeBase64String(bytes);
	}
	
	public static String encodeBase64String(String text){
		return encodeBase64String(text.getBytes());
	}

	public static byte[] decodeBase64Bytes(String bas64text){
		return Base64.decodeBase64(bas64text);		
	}
	
	public static String decodeBase64String(String bas64text){
		return new String(Base64.decodeBase64(bas64text));
	}
	
	public static String decodeBase64String(String bas64text, String charSet){
		try {
			return new String(Base64.decodeBase64(bas64text),charSet);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Exception decoding string for charset : {}, using default::"+charSet);
			return decodeBase64String(bas64text);
		}
	}
	
	public static String urlDecode(String data, String charSet) {
		try {
			return URLDecoder.decode(data,charSet);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Exception decoding string for charset : {}, using default:-"+charSet);		
			try {
				return URLDecoder.decode(data,StandardCharsets.UTF_8.toString());
			} catch (UnsupportedEncodingException e1) {
				System.out.println("Exception decoding string for charset : {}, sending data as it is:-"+data);		
			}
		}
		return data;
	}
	
	public static String urlEncode(String data, String charSet) {
		try {
			return URLEncoder.encode(data,charSet);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Exception decoding string for charset : {}, using default:-"+charSet);		
			try {
				return URLEncoder.encode(data,StandardCharsets.UTF_8.toString());
			} catch (UnsupportedEncodingException e1) {
				System.out.println("Exception decoding string for charset : {}, sending data as it is:-"+data);		
			}
		}
		return data;
	}
	
	public static void main(String[] args){
		String text = "Onm0bile";
		String base64Text = EncodingUtil.encodeBase64String(text); // encoding
		System.out.println("Encoded Base64 text : "+base64Text);
		String decodedText = EncodingUtil.decodeBase64String(base64Text); //decoding
		System.out.println("Decoded text : "+decodedText);
	}	


}
