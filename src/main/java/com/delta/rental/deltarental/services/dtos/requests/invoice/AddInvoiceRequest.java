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



    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int rentalId;
}
