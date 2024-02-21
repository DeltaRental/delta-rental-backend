package com.delta.rental.deltarental.services.dtos.requests.rental;

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
public class UpdateRentalRequest {
    @NotNull(message = Messages.IdMessages.ID_NOT_NULL)
    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int id;


    private LocalDate startDate;


    private LocalDate endDate;


    private LocalDate returnDate;

    private String returnLocation;

    //private double startKilometer;

    private double endKilometer;

    private double discount;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int carId;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int customerId;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int employeeId;
}
