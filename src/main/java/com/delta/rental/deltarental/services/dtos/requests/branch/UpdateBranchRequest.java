package com.delta.rental.deltarental.services.dtos.requests.branch;


import com.delta.rental.deltarental.enums.City;
import com.delta.rental.deltarental.services.constants.Messages;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBranchRequest {

    @NotNull(message = Messages.IdMessages.ID_NOT_NULL)
    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int id;

    @NotNull(message = Messages.BranchMessages.BRANCH_NAME_NOT_NULL)
    @Pattern(regexp = Messages.BranchMessages.TURKISH_LETTER_REGEX, message = Messages.BranchMessages.BRANC_NAME_ONLY_LETTERS)
    private String name;

    @NotBlank(message = Messages.BranchMessages.ADDRESS_NOT_BLANK)
    @Length(max=255,message = Messages.BranchMessages.MAX_LENGTH)
    private String address;

    @Pattern(regexp = Messages.GeneralMessages.GSM_REGEX, message = Messages.BranchMessages.PHONE_NUMBER_NOT_BEGIN_ZERO)
    private String gsm;

    @Email(message = Messages.BranchMessages.ENTER_VALID_EMAIL)
    private String email;

    @NotBlank(message = Messages.BranchMessages.MANAGER_NAME_NOT_BLANK)
    @Length(min = 2,message = Messages.BranchMessages.MANAGER_NAME_LENGTH_MIN_TWO_LETTERS)
    private String managerName;

    @NotBlank(message = Messages.BranchMessages.POSTAL_CODE_NOT_BLANK)
    @Length(min=5,max = 5,message = Messages.BranchMessages.POSTAL_CODE_LENGTH_FIVE_DIGITS)
    private String postCode;

    private City city;

}
