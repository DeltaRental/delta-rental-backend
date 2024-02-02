package com.delta.rental.deltarental.services.dtos.requests.payment;

import com.google.gson.annotations.SerializedName;

public class AddPaymentRequest {
    @SerializedName("items")
    Object[] items;

    public Object[] getItems() {
        return items;
    }
}
