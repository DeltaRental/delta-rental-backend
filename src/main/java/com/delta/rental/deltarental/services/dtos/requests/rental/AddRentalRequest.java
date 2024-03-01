package com.delta.rental.deltarental.services.dtos.requests.rental;

import com.delta.rental.deltarental.services.constants.Messages;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRentalRequest {

    private LocalDate startDate;

    private LocalDate endDate;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int carId;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int userId;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int employeeId;

}
