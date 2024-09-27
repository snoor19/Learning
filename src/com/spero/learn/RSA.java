package com.spero.learn;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RSA {

	static String kPublic = "";
	static String kPrivate = "";

	public RSA() {

	}

	public String Encrypt(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		String encrypted;
		byte[] encryptedBytes;

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(1024);
		KeyPair kp = kpg.genKeyPair();

		PublicKey publicKey = kp.getPublic();
		PrivateKey privateKey = kp.getPrivate();

		byte[] publicKeyBytes = publicKey.getEncoded();
		byte[] privateKeyBytes = privateKey.getEncoded();

		kPublic = bytesToString(publicKeyBytes);
		kPrivate = bytesToString(privateKeyBytes);

		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		encryptedBytes = cipher.doFinal(plain.getBytes());

		encrypted = bytesToString(encryptedBytes);
		return encrypted;

	}

	public String Decrypt(String result) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		byte[] decryptedBytes;

		byte[] byteKeyPrivate = stringToBytes(kPrivate);

		KeyFactory kf = KeyFactory.getInstance("RSA");

		PrivateKey privateKey = null;
		try {

			privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(byteKeyPrivate));

		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		String decrypted;

		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		decryptedBytes = cipher.doFinal(stringToBytes(result));
		decrypted = new String(decryptedBytes);
		return decrypted;

	}

	public String bytesToString(byte[] b) {
		byte[] b2 = new byte[b.length + 1];
		b2[0] = 1;
		System.arraycopy(b, 0, b2, 1, b.length);
		return new BigInteger(b2).toString(36);
	}

	public static String encrypt(String data, String privateKey, String publicKey) {
		String encryptionAlgorithm = "RSA/ECB/PKCS1Padding";
		String encrypted = "";
		try {
			Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, ((privateKey == null || privateKey.isEmpty()) ? getPublicKey(publicKey)
					: getPrivateKey(privateKey)));
			final byte[] crypted = cipher.doFinal(data.getBytes());
			encrypted = org.apache.commons.codec.binary.Base64.encodeBase64String(crypted);
		} catch (Exception e) {
			System.out.println("Exception occured::" + e);
		}
		return encrypted;
	}

	public static String decrypt(String data, String privateKey, String publicKey) {
		String encryptionAlgorithm = "RSA/ECB/PKCS1Padding";
		String encrypted = "";
		try {
			Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, ((privateKey == null || privateKey.isEmpty()) ? getPublicKey(publicKey)
					: getPrivateKey(privateKey)));
			final byte[] crypted = cipher.doFinal(Base64.getDecoder().decode(data));
			encrypted = new String(crypted);
		} catch (Exception e) {
			System.out.println("Exception occured::" + e);
		}
		return encrypted;
	}

	private static PublicKey getPublicKey(String base64PublicKey) {
		PublicKey publicKey = null;
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (Exception e) {
			System.out.println("Exception occured::" + e);
		}
		return publicKey;
	}

	private static PrivateKey getPrivateKey(String base64PrivateKey) {
		PrivateKey privateKey = null;
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (Exception e) {
			System.out.println("Exception occured::" + e);
		}
		try {
			privateKey = keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			System.out.println("Exception occured::" + e);
		}
		return privateKey;
	}

	public byte[] stringToBytes(String s) {
		byte[] b2 = new BigInteger(s, 36).toByteArray();
		return Arrays.copyOfRange(b2, 1, b2.length);
	}

	public static void main(String[] args) {

		Map<String, Object> senData = new HashMap<>();
		senData.put("merchantId", "mer082738712637");
		senData.put("dateTime", "201910291828807");
		senData.put("orderId", "ord0000001");
		senData.put("challenge", "695EF3869547B6C07F5D56399935FB72D21737EA");
		String str = "";
		try {
			str = new ObjectMapper().writeValueAsString(senData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		try {

			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
			String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";

			String encrypt = Base64.getEncoder().encodeToString(RSAUtils.encrypt(str, publicKey));
			System.out.println("encrypt data is:" + encrypt);

			String decrypt = RSAUtils.decrypt(encrypt, privateKey);
			System.out.println("decrypt data is: " + decrypt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
