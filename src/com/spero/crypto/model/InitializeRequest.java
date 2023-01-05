package com.spero.crypto.model;

/**
 * @author Imrul
 * created on 3/19/2020 11:30 PM
 * @project ecommerce
 */
public class InitializeRequest {
    private String accountNumber;
    private String dateTime;
    private String sensitiveData;
    private String signature;

    public InitializeRequest() {
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSensitiveData() {
        return this.sensitiveData;
    }

    public void setSensitiveData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
