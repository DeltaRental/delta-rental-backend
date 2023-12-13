package com.delta.rental.deltarental.services.dtos.requests.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    private int year;

    private int kilometer;

    private double dailyPrice;

    private String plate;
}
