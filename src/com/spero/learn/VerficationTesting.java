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
			logger.debug("DigestBytes : {}", digestBytes);
			logger.debug("signatBytes : {}", signatureByte);
			logger.debug(new String(digestBytes, "UTF-8"));
			logger.debug(new String(signatureByte, "UTF-8"));
			// Step -4
			if (!MessageDigest.isEqual(digestBytes, signatureByte)) {
				logger.debug("*Payment* Digest did not match with signature. So rejecting the request");
				return false;
			} else {
				logger.debug("*Payment* Digest matched with signature.");
			}
		} catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
			logger.debug("*Payment * Recieved error while getting hmac digest : {}", e);
			return false;
		}

		return false;
	}

	public static void main(String[] args) {
		String payload = "oVllS3OjnXj5CXRfetjI9Vdea-R_YnYA-RoFFT9yTeE";
		String signature = "yzBV_b4zVsPAnuLBBpcft8SQWR7YVbxoF-3uvS6_oM8=";

		new VerficationTesting().validatePayload(payload, signature);

	}

}
