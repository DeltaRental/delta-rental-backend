package com.delta.rental.deltarental.services.dtos.requests.customer;

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

    @NotNull(message = "Id boş geçilemez")
    @Positive(message = "Id 0'dan küçük olamaz")
    private int id;

    @NotNull(message = "Kimlik numarası boş geçilemez")
    @Size(min=11, max=11, message = "Kimlik numarası 11 haneli olmak zorundadır.")
    @Pattern(regexp = "^[0-9]+$", message = "Kimlik numarası sadece rakamlardan oluşmalıdır.")
    private String nationalityId;

    @Positive(message = "User Id 0'dan küçük olamaz")
    private int userId;

}
