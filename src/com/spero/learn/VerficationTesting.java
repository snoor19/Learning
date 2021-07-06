package com.spero.learn;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerficationTesting {

	private static final Logger logger = LoggerFactory.getLogger(VerficationTesting.class);

	public byte[] getDecodedBase64(String payload) {
		byte[] decodedByte = Base64.decodeBase64(payload);
		return decodedByte;
	}

	public boolean validatePayload(String payload, String signature) {

		// Step-1
		byte[] signatureByte = getDecodedBase64(signature);
		byte[] payloadByte = getDecodedBase64(payload);

		// Step-2
		byte[] apiKeyByte = getDecodedBase64("1qrTHQVaoAhCvWMQOqDPpIRYP0pfLMFz");

		// Step -3
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(apiKeyByte, "HmacSHA256");
			sha256_HMAC.init(secret_key);
			byte[] digestBytes = sha256_HMAC.doFinal(payloadByte);
			System.out.println("DigestBytes : {}"+ digestBytes);
			System.out.println("signatBytes : {}"+signatureByte);
			System.out.println(new String(digestBytes,"UTF-8"));
			System.out.println(new String(signatureByte, "UTF-8"));
			// Step -4
			if (!MessageDigest.isEqual(digestBytes, signatureByte)) {
				System.out.println("*Payment* Digest did not match with signature. So rejecting the request");
				return false;
			} else {
				System.out.println("*Payment* Digest matched with signature.");
			}
		} catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
			System.out.println("*Payment * Recieved error while getting hmac digest : {}"+ e);
			return false;
		}

		return false;
	}

	public static void main(String[] args) {
		String payload = "{\"msisdn\":\"7795349007\",\"amount\":100,\"currency\":\"INR\",\"mode\":\"credit\",\"info\":\"key:value|key1:value1\",\"srvkey\":\"RBT_ACT_FREEPACK_SC\",\"refid\":\"fdf345345345ergfdgd\",\"account_detail\":{\"bank_name\":\"Kotak\",\"user_name\":\"onmobile\",\"ifsc\":\"KKBK00000\",\"account_no\":\"123456\",\"account_type\":\"savings\"}}";
		String signature = "yzBV_b4zVsPAnuLBBpcft8SQWR7YVbxoF-3uvS6_oM8=";

		new VerficationTesting().validatePayload(payload, signature);

	}

}
