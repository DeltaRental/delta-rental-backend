package com.delta.rental.deltarental.services.dtos.requests.invoice;


import com.delta.rental.deltarental.services.constants.Messages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {


    @NotNull(message = Messages.IdMessages.ID_NOT_NULL)
    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int id;

    private LocalDate date;

    @Positive(message = Messages.InvoiceMessages.INVOICE_AMOUNT_NOT_NEGATIVE)
    private double amount;

    private String address;

    private String name;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int rentalId;
}

