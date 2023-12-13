package com.delta.rental.deltarental.services.dtos.responses.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarResponse {
    private int kilometer;

    private int year;

    private double dailyPrice;

    private String plate;
}
