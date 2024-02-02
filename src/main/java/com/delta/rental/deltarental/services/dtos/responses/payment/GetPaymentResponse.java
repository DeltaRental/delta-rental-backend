package com.delta.rental.deltarental.services.dtos.responses.payment;

public class GetPaymentResponse {
    private String clientSecret;
    public GetPaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
