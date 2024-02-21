package com.delta.rental.deltarental.services.dtos.requests.invoice;


import com.delta.rental.deltarental.services.constants.Messages;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInvoiceRequest {


    private LocalDate date;

    @Positive(message = Messages.InvoiceMessages.INVOICE_AMOUNT_NOT_NEGATIVE)
    private int amount;

    private String address;

    private String name;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int rentalId;
}
