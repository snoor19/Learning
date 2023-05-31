package com.spero.crypto.model;

/**
 * @author Imrul
 * created on 3/19/2020 11:31 PM
 * @project ecommerce
 */
public class InitializeResponse {
    private String sensitiveData;
    private String signature;

    public InitializeResponse() {
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

    @Override
    public String toString() {
        return "InitializeResponse{" +
                "sensitiveData='" + sensitiveData + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
