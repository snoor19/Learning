package com.spero.learn;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.onmobile.prism.util.EncodingUtil;

public class EncryptionHelper {

	private Cipher encryptCipher = null;
	private Cipher decryptCipher = null;

	private String passKey;
	private String iv;

	public EncryptionHelper() {
	}

	public EncryptionHelper(String passKey, String iv) {
		this.passKey = passKey;
		this.iv = iv;
	}

	public void init() throws Exception {
		encryptCipher = getCipher(Cipher.ENCRYPT_MODE, passKey, iv);
		decryptCipher = getCipher(Cipher.DECRYPT_MODE, passKey, iv);

	}

	private Cipher getCipher(int mode, String passKey, String iv)
			throws Exception {
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec skey = new SecretKeySpec(passKey.getBytes("utf-8"), "AES");
		byte[] ivByteArray = getKeyBytes(iv);
		c.init(mode, skey, new IvParameterSpec(ivByteArray));
		return c;
	}

	public String encrypt(String data) throws IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		byte[] encrypted = encryptCipher.doFinal(data.getBytes("UTF-8"));
		return DatatypeConverter.printHexBinary(encrypted);
	}

	public String decrypt(String data) throws IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		byte[] decrypted = decryptCipher.doFinal(DatatypeConverter
				.parseHexBinary(data));
		return new String(decrypted, "UTF-8");
	}

	private byte[] getKeyBytes(String key) throws UnsupportedEncodingException {
		byte[] arrayOfByte1 = new byte[16];
		byte[] arrayOfByte2 = key.getBytes("UTF-8");
		System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0,
				Math.min(arrayOfByte2.length, arrayOfByte1.length));
		return arrayOfByte1;
	}
	
	private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }
	
	public static void main(String args[]) {

		EncryptionHelper neoEncryption = null;

		try {
			/*
			 * SecureRandom random = new SecureRandom(); byte[] salt = new byte[16];
			 * random.nextBytes(salt); KeySpec spec = new
			 * PBEKeySpec("password".toCharArray(), salt, 1000000, 256); SecretKeyFactory f
			 * = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); byte[] key =
			 * f.generateSecret(spec).getEncoded();
			 */

			byte[] nonce = new byte[16];
		    new SecureRandom().nextBytes(nonce);
		    String iv = convertBytesToHex(nonce);
		    System.out.println("Iv value::"+iv);
		    String IV = EncodingUtil.decodeBase64String("REFSU0hBTkdPUEFMUjk5MA==");
			String KEYS = EncodingUtil.decodeBase64String("MDEyMzQ1Njc4OTEyMzQ1Ng==");
			neoEncryption = new EncryptionHelper(KEYS, iv);
			neoEncryption.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String str = neoEncryption.encrypt("9008763580");
			System.out.println("Encrypt data::"+str);
			System.out.println("Data"+neoEncryption.decrypt("250C521E90A105ED41CF4B6D1762FA2F"));
//			System.out.println(neoEncryption
//					.decrypt("606ABE662A6C3CFE6671854D500CB473"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
