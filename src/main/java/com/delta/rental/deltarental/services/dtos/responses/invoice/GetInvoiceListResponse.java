package com.delta.rental.deltarental.services.dtos.responses.invoice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceListResponse {
    private LocalDate date;
    private int amount;
    private String address;
    private String name;
    private int rentalId;
}
