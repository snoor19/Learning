/*
 * *
 *  * *****************************************************************
 *  * Copyright  2020.
 *  * All Rights Reserved to
 *  * NAGAD.
 *  * Redistribution or Using any part of source code or binary
 *  * can not be done without permission of NAGAD.
 *  * *****************************************************************
 *  *
 *  * @author - Md. Imrul Hasan
 *  * @email - imrul.hasan@nagad.com.bd
 *  * @date: 8/25/20, 3:55 PM
 *  * ****************************************************************
 *
 */

package com.spero.crypto.model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.core.util.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spero.crypto.exception.AsymmetricEncryptionFailure;
import com.spero.crypto.exception.SymmetricEncryptionFailure;

public class CryptoUtility {
    public CryptoUtility() {
    }

    public byte[] decrypt(PrivateKey privateKey, byte[] encrypted) {
        Object var4 = null;

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(2, privateKey);
            byte[] plainText = cipher.doFinal(encrypted);
            return plainText;
        } catch (NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException var6) {
            throw new AsymmetricEncryptionFailure(var6);
        }
    }

    public byte[] decryptWithAESAndIV(String keyString, String ivString, byte[] encrypted) {
        Object var4 = null;

        try {
            //IvParameterSpec iv = new IvParameterSpec(ivString.getBytes("UTF-8"));
            Random rand = new SecureRandom();
            byte[] bytes = new byte[16];
            rand.nextBytes(bytes);
            IvParameterSpec iv = new IvParameterSpec(bytes);
            SecretKeySpec skeySpec = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] plainText = cipher.doFinal(encrypted);
            return plainText;
        } catch (NoSuchAlgorithmException var6) {
            throw new AsymmetricEncryptionFailure(var6);
        } catch (NoSuchPaddingException var7) {
            throw new AsymmetricEncryptionFailure(var7);
        } catch (InvalidKeyException var8) {
            throw new AsymmetricEncryptionFailure(var8);
        } catch (BadPaddingException var9) {
            throw new AsymmetricEncryptionFailure(var9);
        } catch (IllegalBlockSizeException var10) {
            throw new AsymmetricEncryptionFailure(var10);
        } catch (InvalidAlgorithmParameterException var11) {
            throw new AsymmetricEncryptionFailure(var11);
        } catch (UnsupportedEncodingException var12) {
            throw new AsymmetricEncryptionFailure(var12);
        }
    }

    public Boolean verifySign(PublicKey pgPublicKey, byte[] plain, byte[] sign) {
        Boolean verify = null;

        try {
            Signature instance = Signature.getInstance("SHA256withRSA");
            instance.initVerify(pgPublicKey);
            instance.update(plain);
            verify = instance.verify(sign);
            return verify;
        } catch (SignatureException | InvalidKeyException | NoSuchAlgorithmException var7) {
            throw new AsymmetricEncryptionFailure(var7);
        }
    }

    public byte[] sign(PrivateKey merchantPrivateKey, byte[] bytes) {
        Object var4 = null;

        try {
            Signature instance = Signature.getInstance("SHA256withRSA");
            instance.initSign(merchantPrivateKey);
            instance.update(bytes);
            byte[] sign = instance.sign();
            return sign;
        } catch (SignatureException | InvalidKeyException | NoSuchAlgorithmException var6) {
            throw new AsymmetricEncryptionFailure(var6);
        }
    }

    public String signWithHMAC(String hmacKey, byte[] data) {
        String algo = "HMACSHA256";
        ObjectMapper objectMapper = new ObjectMapper();
        String generatedSignature;

        try {
            Mac mac = Mac.getInstance(algo);
            byte[] hmacKeyBytes = DatatypeConverter.parseHexBinary(hmacKey);
            SecretKey secretKey = new SecretKeySpec(hmacKeyBytes, algo);
            mac.init(secretKey);
            byte[] buf = new byte[1024];
            InputStream inputStream = new ByteArrayInputStream(data);
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                mac.update(buf, 0, len);
            }
            generatedSignature = Base64.getEncoder().encodeToString(mac.doFinal());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return generatedSignature;
    }

    public byte[] encrypt(PublicKey pgPublicKey, byte[] rawData) {
        Cipher cipher = null;
        Object var4 = null;

        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, pgPublicKey);
            byte[] result = cipher.doFinal(rawData);
            return result;
        } catch (NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchAlgorithmException var6) {
            throw new AsymmetricEncryptionFailure(var6);
        }
    }

    public byte[] encryptWithAESAndIV(String keyString, String ivString, byte[] rawData) {
        Cipher cipher = null;
        Object var4 = null;

        try {
            IvParameterSpec iv = new IvParameterSpec(ivString.getBytes("UTF-8"));
            /*Random rand = new SecureRandom();
            byte[] bytes = new byte[16];
            rand.nextBytes(bytes);
            IvParameterSpec iv = new IvParameterSpec(bytes);*/
            SecretKeySpec skeySpec = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] result = cipher.doFinal(rawData);
            return result;
        } catch (NoSuchAlgorithmException var6) {
            throw new AsymmetricEncryptionFailure(var6);
        } catch (NoSuchPaddingException var7) {
            throw new AsymmetricEncryptionFailure(var7);
        } catch (BadPaddingException var8) {
            throw new AsymmetricEncryptionFailure(var8);
        } catch (IllegalBlockSizeException var9) {
            throw new AsymmetricEncryptionFailure(var9);
        } catch (InvalidKeyException var10) {
            throw new AsymmetricEncryptionFailure(var10);
        } /*catch (InvalidAlgorithmParameterException var11) {
            throw new AsymmetricEncryptionFailure(var11);
        }*/ catch (UnsupportedEncodingException var12) {
            throw new AsymmetricEncryptionFailure(var12);
        }

    }

    public PublicKey getPublic(String filePath) throws Exception {
        File file = new File(filePath);
        String content ="";
/*
        //Test Start
        String privateKeyBeginning = "-----BEGIN PRIVATE KEY-----\n";
        String privateKeyEnding = "-----END PRIVATE KEY-----";
        String publicKeyBeginning = "-----BEGIN PUBLIC KEY-----\n";
        String publicKeyEnding = "-----END PUBLIC KEY-----";
        if (content.contains(privateKeyBeginning)) {
            content = content.replace(privateKeyBeginning, "");
        }

        if (content.contains(publicKeyBeginning)) {
            content = content.replace(publicKeyBeginning, "");
        }

        if (content.contains(privateKeyEnding)) {
            content = content.replace(privateKeyEnding, "");
        }

        if (content.contains(publicKeyEnding)) {
            content = content.replace(publicKeyEnding, "");
        }

        content = content.replaceAll("\\s", "");
        // Test End
*/

        byte[] keyBytes = Base64.getDecoder().decode(content);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public PrivateKey getPrivate(String filePath) throws Exception {
        File file = new File(filePath);
        String content = "";

        /*//Test Start
        String privateKeyBeginning = "-----BEGIN PRIVATE KEY-----\n";
        String privateKeyEnding = "-----END PRIVATE KEY-----";
        String publicKeyBeginning = "-----BEGIN PUBLIC KEY-----\n";
        String publicKeyEnding = "-----END PUBLIC KEY-----";
        if (content.contains(privateKeyBeginning)) {
            content = content.replace(privateKeyBeginning, "");
        }

        if (content.contains(publicKeyBeginning)) {
            content = content.replace(publicKeyBeginning, "");
        }

        if (content.contains(privateKeyEnding)) {
            content = content.replace(privateKeyEnding, "");
        }

        if (content.contains(publicKeyEnding)) {
            content = content.replace(publicKeyEnding, "");
        }


        // Test End*/content = content.replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(content);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public String generateRandomString(int size, byte[] seed) {
        SecureRandom secureRandom = new SecureRandom(seed);
        byte[] secure = new byte[size];
        secureRandom.nextBytes(secure);
        return DatatypeConverter.printHexBinary(secure);
    }

    public String generateRandomNumber(int size) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < size; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    public byte[] encryptWithAes(String plainData, byte[] key, byte[] secretIV) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        IvParameterSpec ivParameterSpec = new IvParameterSpec(secretIV);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException var11) {
            throw new SymmetricEncryptionFailure(var11);
        }
        try {
            cipher.init(1, secretKeySpec, ivParameterSpec);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException var10) {
            throw new SymmetricEncryptionFailure(var10);
        }
        try {
            byte[] result = cipher.doFinal(plainData.getBytes(StandardCharsets.UTF_8));
            return result;
        } catch (BadPaddingException | IllegalBlockSizeException var9) {
            throw new SymmetricEncryptionFailure(var9);
        }
    }

    public byte[] decryptWithAes(byte[] encryptedData, byte[] key, byte[] iv) {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException var10) {
            throw new SymmetricEncryptionFailure(var10);
        }

        try {
            cipher.init(2, secretKey, ivParameterSpec);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException var9) {
            throw new SymmetricEncryptionFailure(var9);
        }

        try {
            return cipher.doFinal(encryptedData);
        } catch (BadPaddingException | IllegalBlockSizeException var8) {
            throw new SymmetricEncryptionFailure(var8);
        }
    }



    public PublicKey getPublicTest(String filePath) throws Exception {
        File file = new File(filePath);
        String content = "";
        //Test Start
        String privateKeyBeginning = "-----BEGIN PRIVATE KEY-----\n";
        String privateKeyEnding = "-----END PRIVATE KEY-----";
        String publicKeyBeginning = "-----BEGIN PUBLIC KEY-----\n";
        String publicKeyEnding = "-----END PUBLIC KEY-----";
        if (content.contains(privateKeyBeginning)) {
            content = content.replace(privateKeyBeginning, "");
        }

        if (content.contains(publicKeyBeginning)) {
            content = content.replace(publicKeyBeginning, "");
        }

        if (content.contains(privateKeyEnding)) {
            content = content.replace(privateKeyEnding, "");
        }

        if (content.contains(publicKeyEnding)) {
            content = content.replace(publicKeyEnding, "");
        }

        content = content.replaceAll("\\s", "");
        // Test End

        byte[] keyBytes = Base64.getDecoder().decode(content);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
    
    public static void main(String[] args) {
    	Map<String, Object> senData = new HashMap();
    	senData.put("merchantId", "mer082738712637");
    	senData.put("dateTime","201910291828807");
    	senData.put("orderId","ord0000001");
    	senData.put("challenge","695EF3869547B6C07F5D56399935FB72D21737EA");
    	String str = "";
    	try {
    	str = new ObjectMapper().writeValueAsString(senData);
    	} catch (JsonProcessingException e) {
    	e.printStackTrace();
    	}
    	
    	try {
			CryptoUtility crypto = new CryptoUtility();

			String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjBH1pFNSSRKPuMcNxmU5jZ1x8K9LPFM4XSu11m7uCfLUSE4SEjL30w3ockFvwAcuJffCUwtSpbjr34cSTD7EFG1Jqk9Gg0fQCKvPaU54jjMJoP2toR9fGmQV7y9fz31UVxSk97AqWZZLJBT2lmv76AgpVV0k0xtb/0VIv8pd/j6TIz9SFfsTQOugHkhyRzzhvZisiKzOAAWNX8RMpG+iqQi4p9W9VrmmiCfFDmLFnMrwhncnMsvlXB8QSJCq2irrx3HG0SJJCbS5+atz+E1iqO8QaPJ05snxv82Mf4NlZ4gZK0Pq/VvJ20lSkR+0nk+s/v3BgIyle78wjZP1vWLU4wIDAQAB";

			byte[] encrypt = crypto.encrypt(crypto.getPublic(publicKey), str.getBytes("UTF-8"));
			System.out.println("encrypt data is:"+org.apache.commons.codec.binary.Base64.encodeBase64String(encrypt));

			String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJakyLqojWTDAVUdNJLvuXhROV+LXymqnukBrmiWwTYnJYm9r5cKHj1hYQRhU5eiy6NmFVJqJtwpxyyDSCWSoSmIQMoO2KjYyB5cDajRF45v1GmSeyiIn0hl55qM8ohJGjXQVPfXiqEB5c5REJ8Toy83gzGE3ApmLipoegnwMkewsTNDbe5xZdxN1qfKiRiCL720FtQfIwPDp9ZqbG2OQbdyZUB8I08irKJ0x/psM4SjXasglHBK5G1DX7BmwcB/PRbC0cHYy3pXDmLI8pZl1NehLzbav0Y4fP4MdnpQnfzZJdpaGVE0oI15lq+KZ0tbllNcS+/4MSwW+afvOw9bazAgMBAAECggEAIkenUsw3GKam9BqWh9I1p0Xmbeo+kYftznqai1pK4McVWW9//+wOJsU4edTR5KXK1KVOQKzDpnf/CU9SchYGPd9YScI3n/HR1HHZW2wHqM6O7na0hYA0UhDXLqhjDWuM3WEOOxdE67/bozbtujo4V4+PM8fjVaTsVDhQ60vfv9CnJJ7dLnhqcoovidOwZTHwG+pQtAwbX0ICgKSrc0elv8ZtfwlEvgIrtSiLAO1/CAf+uReUXyBCZhS4Xl7LroKZGiZ80/JE5mc67V/yImVKHBe0aZwgDHgtHh63/50/cAyuUfKyreAH0VLEwy54UCGramPQqYlIReMEbi6U4GC5AQKBgQDfDnHCH1rBvBWfkxPivl/yNKmENBkVikGWBwHNA3wVQ+xZ1Oqmjw3zuHY0xOH0GtK8l3Jy5dRL4DYlwB1qgd/Cxh0mmOv7/C3SviRk7W6FKqdpJLyaE/bqI9AmRCZBpX2PMje6Mm8QHp6+1QpPnN/SenOvoQg/WWYM1DNXUJsfMwKBgQCdtddE7A5IBvgZX2o9vTLZY/3KVuHgJm9dQNbfvtXw+IQfwssPqjrvoU6hPBWHbCZl6FCl2tRh/QfYR/N7H2PvRFfbbeWHw9+xwFP1pdgMug4cTAt4rkRJRLjEnZCNvSMVHrri+fAgpv296nOhwmY/qw5Smi9rMkRY6BoNCiEKgQKBgAaRnFQFLF0MNu7OHAXPaW/ukRdtmVeDDM9oQWtSMPNHXsx+crKY/+YvhnujWKwhphcbtqkfj5L0dWPDNpqOXJKV1wHt+vUexhKwus2mGF0flnKIPG2lLN5UU6rs0tuYDgyLhAyds5ub6zzfdUBG9Gh0ZrfDXETRUyoJjcGChC71AoGAfmSciL0SWQFU1qjUcXRvCzCK1h25WrYS7E6pppm/xia1ZOrtaLmKEEBbzvZjXqv7PhLoh3OQYJO0NM69QMCQi9JfAxnZKWx+m2tDHozyUIjQBDehve8UBRBRcCnDDwU015lQN9YNb23Fz+3VDB/LaF1D1kmBlUys3//r2OV0Q4ECgYBnpo6ZFmrHvV9IMIGjP7XIlVa1uiMCt41FVyINB9SJnamGGauW/pyENvEVh+ueuthSg37e/l0Xu0nm/XGqyKCqkAfBbL2Uj/j5FyDFrpF27PkANDo99CdqL5A4NQzZ69QRlCQ4wnNCq6GsYy2WEJyU2D+K8EBSQcwLsrI7QL7fvQ==";
			byte[] decrypt = crypto.decrypt( crypto.getPrivate(privateKey), encrypt);
			System.out.println("decrypt data is: "+new String(decrypt, Charset.forName("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
