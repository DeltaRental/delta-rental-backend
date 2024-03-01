package com.delta.rental.deltarental.services.dtos.requests.customer;

import com.delta.rental.deltarental.services.constants.Messages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

    @NotNull(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int id;

    @NotNull(message = Messages.CustomerMessages.CUSTOMER_NATIONALITY_ID_NOT_NULL)
    @Size(min=11, max=11, message = Messages.CustomerMessages.CUSTOMER_NATIONALITY_ID_LENGTH_ELEVEN_DIGITS)
    @Pattern(regexp = Messages.CustomerMessages.CUSTOMER_NATIONALITY_ID_ONLY_NUMBERS_REGEX, message = Messages.CustomerMessages.CUSTOMER_NATIONALITY_ID_ONLY_NUMBERS)
    private String nationalityId;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int userId;

}
