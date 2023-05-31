package com.spero.crypto.model;

/**
 * @author Imrul
 * created on 3/20/2020 12:22 AM
 * @project ecommerce
 */
public class SensitiveDataInitializeResponse {
    private String paymentReferenceId;
    private String challenge;
    private String acceptDateTime;

    public SensitiveDataInitializeResponse() {
    }

    public String getPaymentReferenceId() {
        return this.paymentReferenceId;
    }

    public void setPaymentReferenceId(String paymentReferenceId) {
        this.paymentReferenceId = paymentReferenceId;
    }

    public String getChallenge() {
        return this.challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getAcceptDateTime() {
        return this.acceptDateTime;
    }

    public void setAcceptDateTime(String acceptDateTime) {
        this.acceptDateTime = acceptDateTime;
    }

    @Override
    public String toString() {
        return "SensitiveDataInitializeResponse{" +
                "paymentReferenceId='" + paymentReferenceId + '\'' +
                ", challenge='" + challenge + '\'' +
                ", acceptDateTime='" + acceptDateTime + '\'' +
                '}';
    }
}
