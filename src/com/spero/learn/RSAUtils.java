package com.spero.learn;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RSAUtils {

	private static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjBH1pFNSSRKPuMcNxmU5jZ1x8K9LPFM4XSu11m7uCfLUSE4SEjL30w3ockFvwAcuJffCUwtSpbjr34cSTD7EFG1Jqk9Gg0fQCKvPaU54jjMJoP2toR9fGmQV7y9fz31UVxSk97AqWZZLJBT2lmv76AgpVV0k0xtb/0VIv8pd/j6TIz9SFfsTQOugHkhyRzzhvZisiKzOAAWNX8RMpG+iqQi4p9W9VrmmiCfFDmLFnMrwhncnMsvlXB8QSJCq2irrx3HG0SJJCbS5+atz+E1iqO8QaPJ05snxv82Mf4NlZ4gZK0Pq/VvJ20lSkR+0nk+s/v3BgIyle78wjZP1vWLU4wIDAQAB";
    private static String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJakyLqojWTDAVUdNJLvuXhROV+LXymqnukBrmiWwTYnJYm9r5cKHj1hYQRhU5eiy6NmFVJqJtwpxyyDSCWSoSmIQMoO2KjYyB5cDajRF45v1GmSeyiIn0hl55qM8ohJGjXQVPfXiqEB5c5REJ8Toy83gzGE3ApmLipoegnwMkewsTNDbe5xZdxN1qfKiRiCL720FtQfIwPDp9ZqbG2OQbdyZUB8I08irKJ0x/psM4SjXasglHBK5G1DX7BmwcB/PRbC0cHYy3pXDmLI8pZl1NehLzbav0Y4fP4MdnpQnfzZJdpaGVE0oI15lq+KZ0tbllNcS+/4MSwW+afvOw9bazAgMBAAECggEAIkenUsw3GKam9BqWh9I1p0Xmbeo+kYftznqai1pK4McVWW9//+wOJsU4edTR5KXK1KVOQKzDpnf/CU9SchYGPd9YScI3n/HR1HHZW2wHqM6O7na0hYA0UhDXLqhjDWuM3WEOOxdE67/bozbtujo4V4+PM8fjVaTsVDhQ60vfv9CnJJ7dLnhqcoovidOwZTHwG+pQtAwbX0ICgKSrc0elv8ZtfwlEvgIrtSiLAO1/CAf+uReUXyBCZhS4Xl7LroKZGiZ80/JE5mc67V/yImVKHBe0aZwgDHgtHh63/50/cAyuUfKyreAH0VLEwy54UCGramPQqYlIReMEbi6U4GC5AQKBgQDfDnHCH1rBvBWfkxPivl/yNKmENBkVikGWBwHNA3wVQ+xZ1Oqmjw3zuHY0xOH0GtK8l3Jy5dRL4DYlwB1qgd/Cxh0mmOv7/C3SviRk7W6FKqdpJLyaE/bqI9AmRCZBpX2PMje6Mm8QHp6+1QpPnN/SenOvoQg/WWYM1DNXUJsfMwKBgQCdtddE7A5IBvgZX2o9vTLZY/3KVuHgJm9dQNbfvtXw+IQfwssPqjrvoU6hPBWHbCZl6FCl2tRh/QfYR/N7H2PvRFfbbeWHw9+xwFP1pdgMug4cTAt4rkRJRLjEnZCNvSMVHrri+fAgpv296nOhwmY/qw5Smi9rMkRY6BoNCiEKgQKBgAaRnFQFLF0MNu7OHAXPaW/ukRdtmVeDDM9oQWtSMPNHXsx+crKY/+YvhnujWKwhphcbtqkfj5L0dWPDNpqOXJKV1wHt+vUexhKwus2mGF0flnKIPG2lLN5UU6rs0tuYDgyLhAyds5ub6zzfdUBG9Gh0ZrfDXETRUyoJjcGChC71AoGAfmSciL0SWQFU1qjUcXRvCzCK1h25WrYS7E6pppm/xia1ZOrtaLmKEEBbzvZjXqv7PhLoh3OQYJO0NM69QMCQi9JfAxnZKWx+m2tDHozyUIjQBDehve8UBRBRcCnDDwU015lQN9YNb23Fz+3VDB/LaF1D1kmBlUys3//r2OV0Q4ECgYBnpo6ZFmrHvV9IMIGjP7XIlVa1uiMCt41FVyINB9SJnamGGauW/pyENvEVh+ueuthSg37e/l0Xu0nm/XGqyKCqkAfBbL2Uj/j5FyDFrpF27PkANDo99CdqL5A4NQzZ69QRlCQ4wnNCq6GsYy2WEJyU2D+K8EBSQcwLsrI7QL7fvQ==";
    private static String dycPrivateKey = "";
    static byte[] KPG_DefaultSeed = ("nagad-dfs-service-ltd" + System.currentTimeMillis() + "").getBytes();
    private static int seedSize = 20;
    private static String dateFormatStr = "yyyyMMddHHmmss";
    private static String merchantId = "683002007104225";
    private static Map<String, Object> rawData = new HashMap();
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    private static String sign(byte[] data)
            throws Exception {
        if (data == null) {
            return StringUtils.EMPTY;
        }
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(getPrivateKey(privateKey));
        signature.update(data);

        return org.apache.commons.codec.binary.Base64.encodeBase64String(signature.sign());
    }
    
    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		/*
		 * byte[] enBytes = null; for (int i = 0; i <data.length(); i += 64) { //Note to
		 * use a multiple of 2, otherwise it will appear after encryption The content of
		 * the decryption is garbled byte[] doFinal =
		 * cipher.doFinal(ArrayUtils.subarray(data.getBytes(), i,i + 64)); enBytes =
		 * ArrayUtils.addAll(enBytes, doFinal); } return enBytes;
		 */
        return cipher.doFinal(data.getBytes());
    }

	public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		/*
		 * StringBuilder sb = new StringBuilder(); for (int i = 0; i < data.length; i +=
		 * 128) { byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i +
		 * 128)); sb.append(new String(doFinal)); } return sb.toString();
		 */
		return new String(cipher.doFinal(data));
	}

    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
    }
    
    public static String generateRandomString(int size, byte[] seed) {
        SecureRandom secureRandom = new SecureRandom(seed);
        byte[] secure = new byte[size];
        secureRandom.nextBytes(secure);
        return DatatypeConverter.printHexBinary(secure);
    }

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        try {
        	String random = generateRandomString(seedSize, KPG_DefaultSeed);
        	 Date date = new Date();
             DateFormat format = new SimpleDateFormat(dateFormatStr);
             String datetime = format.format(date);
             Long orderId = System.currentTimeMillis();
             rawData.put("merchantId", merchantId);
             rawData.put("orderId", orderId.toString());
             rawData.put("datetime", datetime);
             rawData.put("challenge", random);

             String rawDataToBeEncrypted = objectMapper.writeValueAsString(rawData);
            String encryptedString = Base64.getEncoder().encodeToString(encrypt("{\"merchantId\":\"683002007104225\",\"dateTime\":\"20220718134800\",\"orderId\":\"9ff3e200482c44198f33\",\"challenge\":\"C0AB21B9CE4FB7792DF70A37C0EF2A537078D5C\"}", publicKey));
//            String encryptedString = Base64.getEncoder().encodeToString(encrypt(rawDataToBeEncrypted, publicKey));
            String data = "VBX0c6i84xjZbok1jPzCwtyTmbYlS6kGQogshysYwYNJ9pUfdU8frngnpZRP48yVbLmP7KzB19XvnPoIR32mUKGIVrY6hFzp2h+oZnjoFku9pGPiT+rpiVOPqsKgz7rODC6wy4KZyC8MOVNxJUcLZKLATEGVkZMAg1bYB2+CK0VA5XsB/rNfOwKJj/s+CiywAei44otIQ643wrJW4NGGB40sRuYyViXZ9M6C4GzR2LKTZuj1UNDDYfhx1qwT8AQ0yf1Aroj/X3HwCcNpZAcraW5zQ56HwFzgjyfWPCKAxnLoTU0tz0cVWSAEG3mzkqYYIVAOCVDix1+ABQcYTJWQgg==";
//            System.out.println(encryptedString);
//            System.out.println("Sign::"+sign(encryptedString.getBytes()));
            String decryptedString = RSAUtils.decrypt(data, privateKey);
            System.out.println("Dycrpted::"+decryptedString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
