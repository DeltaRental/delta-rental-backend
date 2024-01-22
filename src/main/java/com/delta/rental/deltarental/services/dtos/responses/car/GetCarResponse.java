package com.delta.rental.deltarental.services.dtos.responses.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCarResponse {
    private int id;

    private double kilometer;

    private int year;

    private double dailyPrice;

    private String plate;

    private String modelName;

    private String colorName;

    private boolean status;
}
