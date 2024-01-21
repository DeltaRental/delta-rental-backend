package com.delta.rental.deltarental.services.dtos.requests.employee;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeRequest {

    @Positive(message = "Maaş 0'dan küçük olamaz.")
    private double salary;

    @Positive(message = "User id 0' dan küçük bir değer olamaz.")
    private int userId;

}
