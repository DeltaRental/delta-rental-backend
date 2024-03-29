package com.delta.rental.deltarental.services.dtos.responses.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceResponse {
    private LocalDate date;
    private double amount;
    private String name;
    private int rentalId;
}
