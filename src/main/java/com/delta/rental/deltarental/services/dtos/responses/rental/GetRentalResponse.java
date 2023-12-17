package com.delta.rental.deltarental.services.dtos.responses.rental;

import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerListResponse;
import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalResponse {
    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate returnDate;

    private double startKilometer;

    private Double endKilometer;

    private double totalPrice;

    private double discount;

    private GetCarListResponse car;

    private GetCustomerListResponse customer;

    private GetEmployeeListResponse employee;
}
