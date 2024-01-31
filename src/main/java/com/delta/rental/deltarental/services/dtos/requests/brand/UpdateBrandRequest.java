package com.delta.rental.deltarental.services.dtos.requests.brand;


import com.delta.rental.deltarental.services.constants.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
    @NotNull(message = Messages.IdMessages.ID_NOT_NULL)
    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int id;

    @NotBlank(message = Messages.BrandMessages.BRAND_NAME_NOT_BLANK)
    @Length(min = 2,message = Messages.BrandMessages.BRAND_NAME_LENGTH_MIN_TWO_LETTERS)
    private String name;
}
