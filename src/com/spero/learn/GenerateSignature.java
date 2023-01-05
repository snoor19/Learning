package com.spero.learn;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class GenerateSignature {
	
	private String buildSignature(String message, String secret) {

	try {
		byte[] key = secret.getBytes("UTF-8");
		byte[] mess = message.getBytes("UTF-8");
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key, "HmacSHA256");
		sha256_HMAC.init(secret_key);
		byte[] digestBytes = sha256_HMAC.doFinal(mess);
//		String.Concat(Array.ConvertAll(hash, x => x.ToString("X2")));
		String sign = java.util.Base64.getEncoder().encodeToString(digestBytes);
//		sign = sign.trim()('=').Replace('+', '-').Replace('/', '_');
		int index = sign.lastIndexOf("=");
		if( index>=0 )
			sign = new StringBuilder(sign).replace(index, index+1,"").toString();
		sign = sign.replace('+', '-').replace('/', '_');
		return sign;
	} catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
		e.printStackTrace();
	} 
	return null;
}

public static void main(String[] args) {
	String walletId = "VeXG0T8aHxssbFk8MRFFcj3o2avY2wZD";
    String message = "{\"msisdn\":\"9876543210\",\"req_ref_id\":\"sfasdfasdfassdv\",\"info\":\"data:test\"}";
    GenerateSignature re = new GenerateSignature();
    String signatureNew = re.buildSignature(message, walletId);
	System.out.println("Signature ::"+signatureNew);
	
//	String actualSign = "oVllS3OjnXj5CXRfetjI9Vdea-R_YnYA-RoFFT9yTeE";
	
//	System.out.println("Resp::"+actualSign.equals(signatureNew));
}
}
