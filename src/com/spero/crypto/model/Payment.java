package com.spero.crypto.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Payment {
    String base_url = "http://sandbox.mynagad.com:10080/remote-payment-gateway-1.0";
    byte[] KPG_DefaultSeed = ("nagad-dfs-service-ltd" + System.currentTimeMillis() + "").getBytes();
    private int seedSize = 20;
    private String dateFormatStr = "yyyyMMddHHmmss";
    private String merchantId = "683002007104225";
    private Map<String, Object> rawData = new HashMap();
    private ObjectMapper objectMapper;
    private String gwPublicKeyPath = "keys/Payment_Gateway_PublicKey.pem";
    private String privateKeyPath = "keys/Merchant_MC00X6UV001226_1577681573300_pri.pem";

    public Payment() {
        this.objectMapper = new ObjectMapper();
    }

    public void pay() {
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            CryptoUtility crypto = new CryptoUtility();
            String random = crypto.generateRandomString(this.seedSize, this.KPG_DefaultSeed);
            Date date = new Date();
            DateFormat format = new SimpleDateFormat(this.dateFormatStr);
            String datetime = format.format(date);
            Long orderId = System.currentTimeMillis();
            rawData.put("merchantId", this.merchantId);
            rawData.put("orderId", orderId.toString());
            rawData.put("datetime", datetime);
            rawData.put("challenge", random);

            String rawDataToBeEncrypted = this.objectMapper.writeValueAsString(this.rawData);
            byte[] rawEncryptedBytes = crypto.encrypt(crypto.getPublic(this.gwPublicKeyPath), rawDataToBeEncrypted.getBytes("UTF-8"));
            byte[] rawSignatureBytes = crypto.sign(crypto.getPrivate(this.privateKeyPath), rawDataToBeEncrypted.getBytes("UTF-8"));
            String sensitiveData = encoder.encodeToString(rawEncryptedBytes);
            String signature = encoder.encodeToString(rawSignatureBytes);


            System.out.println("Payment Initializing =====");
            System.out.println("client random number generated : " + random);
            System.out.println("===========================================================================");
            System.out.println("Initialization -- request");
            System.out.println("...........................................................................");
            System.out.println("===========================================================================");
            System.out.println("Initialization Data: " + rawDataToBeEncrypted);
            System.out.println("...........................................................................");

            System.out.println("===========================================================================");
            System.out.println("Initialization Sensitive Data: " + sensitiveData);
            System.out.println("...........................................................................");

            System.out.println("===========================================================================");
            System.out.println("Initialization Signature: " + signature);
            System.out.println("...........................................................................");

        

            InitializeResponse response = null;
			byte[] rawDecryptedBytes = crypto.decrypt(crypto.getPrivate(privateKeyPath), decoder.decode(response.getSensitiveData()));
            String responseSensitiveData = new String(rawDecryptedBytes, Charset.forName("UTF-8"));
            ObjectMapper objectMapper = new ObjectMapper();
            SensitiveDataInitializeResponse sensitiveDataInitializeResponse = (SensitiveDataInitializeResponse) objectMapper.readValue(responseSensitiveData, SensitiveDataInitializeResponse.class);
            System.out.println("Initialization Response Sensitive Data: " + responseSensitiveData.toString());
            Boolean verify = crypto.verifySign(crypto.getPublic(this.gwPublicKeyPath), rawDecryptedBytes, decoder.decode(response.getSignature()));

            System.out.println("Sign Verification: " + verify);

            System.out.println("===========================================================================");
            System.out.println("Complete -- request");
            System.out.println("...........................................................................");
            rawData.clear();
            rawData.put("merchantId", this.merchantId);
            rawData.put("orderId", orderId.toString());
            rawData.put("currencyCode", "050");
            rawData.put("amount", "11");
            rawData.put("challenge", sensitiveDataInitializeResponse.getChallenge());

            rawDataToBeEncrypted = this.objectMapper.writeValueAsString(this.rawData);
            rawEncryptedBytes = crypto.encrypt(crypto.getPublic(this.gwPublicKeyPath), rawDataToBeEncrypted.getBytes("UTF-8"));
            rawSignatureBytes = crypto.sign(crypto.getPrivate(this.privateKeyPath), rawDataToBeEncrypted.getBytes("UTF-8"));
            sensitiveData = encoder.encodeToString(rawEncryptedBytes);
            signature = encoder.encodeToString(rawSignatureBytes);

            System.out.println("...........................................................................");
            System.out.println("===========================================================================");
            System.out.println("Order Data: " + rawDataToBeEncrypted);
            System.out.println("...........................................................................");

            System.out.println("===========================================================================");
            System.out.println("Order Sensitive Data: " + sensitiveData);
            System.out.println("...........................................................................");

            System.out.println("===========================================================================");
            System.out.println("Order Signature: " + signature);
            System.out.println("...........................................................................");


            String body1 = "{     \"sensitiveData\": \""+sensitiveData+"\",     \"signature\": \""+signature+"\",     \"merchantCallbackURL\": \"http://sandbox.mynagad.com:10707/merchant-server/web/confirm\",     \"additionalMerchantInfo\": {      \"productName\":\"shirt\",      \"productCount\":1     } } ";
        } catch (Exception e) {
            System.out.println("ERROR: Error Occurred and exception is: " + e.toString());
        }
    }

    public void cancel() {
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            CryptoUtility crypto = new CryptoUtility();
            String paymentRefId = "MDgwNTEyMTAwMjA1MC42ODMwMDIwMDcxMDQyMjUuMTU5NjYwODAyNjc5NS5kZDk1ZDg2OTUyMzNhNWIzMGRkYw==";
            String orderId = "1596608026795";
            rawData.put("merchantId", this.merchantId);
            rawData.put("originalRequestDate", "20200805");
            rawData.put("orderId", "1596608026795");
            rawData.put("originalAmount", "11");
            rawData.put("cancelAmount", "7");
            rawData.put("referenceNo", "10101013");
            rawData.put("referenceMessage", "Test Cancel transaction");

            

            System.out.println("===========================================================================");
            System.out.println("Cancel/Refund -- Transaction");
            System.out.println("...........................................................................");


            String rawDataToBeEncrypted = this.objectMapper.writeValueAsString(this.rawData);
            byte[] rawEncryptedBytes = crypto.encrypt(crypto.getPublic(this.gwPublicKeyPath), rawDataToBeEncrypted.getBytes("UTF-8"));
            byte[] rawSignatureBytes = crypto.sign(crypto.getPrivate(this.privateKeyPath), rawDataToBeEncrypted.getBytes("UTF-8"));
            String sensitiveData = encoder.encodeToString(rawEncryptedBytes);
            String signature = encoder.encodeToString(rawSignatureBytes);

            System.out.println("...........................................................................");
            System.out.println("===========================================================================");
            System.out.println("Order cancellation Data: " + rawDataToBeEncrypted);
            System.out.println("...........................................................................");

            System.out.println("===========================================================================");
            System.out.println("Order cancellation Sensitive Data: " + sensitiveData);
            System.out.println("...........................................................................");

            System.out.println("===========================================================================");
            System.out.println("Order cancellation Signature: " + signature);
            System.out.println("...........................................................................");


            String body = "{     \"sensitiveDataCancelRequest\": \""+sensitiveData+"\",     \"signature\": \""+signature+"\"} ";

            InitializeResponse response = null;
			byte[] rawDecryptedBytes = crypto.decrypt(crypto.getPrivate(privateKeyPath), decoder.decode(response.getSensitiveData()));
            String responseSensitiveData = new String(rawDecryptedBytes, Charset.forName("UTF-8"));
            System.out.println("Initialization Response Sensitive Data: " + responseSensitiveData);
        } catch (Exception e) {
            System.out.println("ERROR: Error Occurred and exception is: " + e.toString());
        }
    }
}
