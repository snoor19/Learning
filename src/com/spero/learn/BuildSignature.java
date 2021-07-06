package com.spero.learn;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class BuildSignature {

	private String buildSignature(String message, String secret) {

		try {
			byte[] key = Base64.decodeBase64(secret);
			byte[] mess = message.getBytes();

			SecretKeySpec secret_key = new SecretKeySpec(key, "HmacSHA256");
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			sha256_HMAC.init(secret_key);
			byte[] digestBytes = sha256_HMAC.doFinal(mess);
			System.out.println("ToHexString::" + toHexString(digestBytes));
			String sign = Base64.encodeBase64String(digestBytes);
//			sign = sign.trim()('=').Replace('+', '-').Replace('/', '_');
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
		String secretKey = "VeXG0T8aHxssbFk8MRFFcj3o2avY2wZD";
		String message = "{\r\n" + "\"msisdn\":\"7795349007\",\r\n" + "\"amount\":100,\r\n"
				+ "\"currency\":\"INR\",\r\n" + "\"mode\":\"credit\",\r\n" + "\"info\":\"key:value|key1:value1\",\r\n"
				+ "\"srvkey\":\"RBT_ACT_FREEPACK_SC\",\r\n" + "\"refid\":\"fdf345345345ergfdgd\",\r\n"
				+ "\"account_detail\":{\r\n" + "\"bank_name\":\"Kotak\",\r\n" + "\"user_name\":\"onmobile\",\r\n"
				+ "\"ifsc\":\"KKBK00000\",\r\n" + "\"account_no\":\"123456\",\r\n" + "\"account_type\":\"savings\"\r\n"
				+ "}\r\n" + "}";

		BuildSignature re = new BuildSignature();
		String signatureNew = re.buildSignature(message, secretKey);
		System.out.println("Signature ::" + signatureNew);

//		String actualSign = "oVllS3OjnXj5CXRfetjI9Vdea-R_YnYA-RoFFT9yTeE";

//		System.out.println("Resp::"+actualSign.equals(signatureNew));
	}

}
