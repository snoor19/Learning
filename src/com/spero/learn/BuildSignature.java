package com.spero.learn;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class BuildSignature {private String buildSignature(String message, String secret) {

	try {
		byte[] key = Base64.decodeBase64(secret);
		byte[] mess = message.getBytes();

		SecretKeySpec secret_key = new SecretKeySpec(key, "HmacSHA256");
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		sha256_HMAC.init(secret_key);
		byte[] digestBytes = sha256_HMAC.doFinal(mess);
		System.out.println("ToHexString::" + toHexString(digestBytes));
		String sign = Base64.encodeBase64String(digestBytes);
//		sign = sign.trim()('=').Replace('+', '-').Replace('/', '_');
		/*
		 * int index = sign.lastIndexOf("="); if( index>=0 ) sign = new
		 * StringBuilder(sign).replace(index, index+1,"").toString(); sign =
		 * sign.replace('+', '-').replace('/', '_');
		 */
		System.out.println("Sign ::" + sign);
		return sign;

	} catch (NoSuchAlgorithmException | InvalidKeyException e) {
		e.printStackTrace();
	}
	return null;
}

public static String toHexString(byte[] bytes) {
	StringBuilder sb = new StringBuilder(bytes.length * 2);

	Formatter formatter = new Formatter(sb);
	for (byte b : bytes) {
		formatter.format("%02x", b);
	}

	return sb.toString();

}

public static void main(String[] args) {
	String secretKey = "3EbBE-L30-PEDU-KjkhxpEop8SYsanOa";
	String message = "siteId=4";

	BuildSignature re = new BuildSignature();
	String signatureNew = re.buildSignature(message, secretKey);
	System.out.println("Signature ::" + signatureNew);

//	String actualSign = "oVllS3OjnXj5CXRfetjI9Vdea-R_YnYA-RoFFT9yTeE";

//	System.out.println("Resp::"+actualSign.equals(signatureNew));
}
}
