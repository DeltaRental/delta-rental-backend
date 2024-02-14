package com.delta.rental.deltarental.services.dtos.requests.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarFilterDto {

    private LocalDate endDate;
    private LocalDate startDate;
    private String startLocation;

}
