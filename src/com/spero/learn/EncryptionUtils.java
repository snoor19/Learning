package com.spero.learn;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class EncryptionUtils {

	public static void main(String[] args) {
		String appId = "80981";
		String appSecret = "9f5a519622a8790b";
		String responseType = "token";
		String timestamp = "1592212221795";
		
		String total = appId+'&'+appSecret+'&'+responseType+'&'+timestamp;
		
		HashFunction hashfunction =  Hashing.md5();
		
		String signature = hashfunction.newHasher().putString(total,Charset.defaultCharset()).hash().toString();
		
		System.out.println("Signature ::"+signature);
		
		// Compute digest
		MessageDigest sha1 = null;
		MessageDigest sha2 = null;
		try {
			sha1 = MessageDigest.getInstance("MD5");
			sha2 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] digest = sha1.digest((total).getBytes());

		try {
			Cipher encryptCipher = getCipher(Cipher.ENCRYPT_MODE);
			
			String finalKey = java.util.Base64.getEncoder().encodeToString(encryptCipher.doFinal(digest));
			
			System.out.println("Final One ::"+finalKey);
			
			String otherWay = bytes2String(encryptCipher.doFinal(digest));
			
			System.out.println("Data ::"+otherWay);
			
			  byte[] encrypted = encryptCipher.doFinal(digest); 
//			  String finalSecretKey = DatatypeConverter.printHexBinary(encrypted);
			  
			  System.out.println("Secret Key ::"+new String(encrypted));
			  
			  // converts to base64 for easier display.
			    byte[] base64Cipher = Base64.encodeBase64(encrypted);
			    
			    System.out.println("Final Secret Message ::"+new String(base64Cipher));
			    
//			    byte[] term = shas
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static Cipher getCipher(int mode)
			throws Exception {
		// Get an instance of the RSA key generator
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("HmacSHA256");
		// Generate the keys — might take sometime on slow computers
		KeyPair myPair = kpg.generateKeyPair();
		Cipher c = Cipher.getInstance("RSA");
		c.init(mode, myPair.getPublic());
		return c;
	}
	
	private static String bytes2String(byte[] bytes) {
	    StringBuilder string = new StringBuilder();
	    for (byte b : bytes) {
	        String hexString = Integer.toHexString(0x00FF & b);
	        string.append(hexString.length() == 1 ? "0" + hexString : hexString);
	    }
	    return string.toString();
	}

}
