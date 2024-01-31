package com.delta.rental.deltarental.services.dtos.requests.employee;

import com.delta.rental.deltarental.services.constants.Messages;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeRequest {

    @Positive(message = Messages.EmployeeMessages.SALARY_NOT_NEGATIVE)
    private double salary;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int userId;

}
