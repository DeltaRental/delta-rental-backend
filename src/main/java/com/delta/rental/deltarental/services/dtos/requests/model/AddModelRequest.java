package com.delta.rental.deltarental.services.dtos.requests.model;


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
public class AddModelRequest {

    @NotBlank(message = "Model adı boş olamaz!")
    @Length(min = 2,message = "Girilen model adı en az 2 harfli olmalıdır.")
    private String name;

    @NotNull(message = "Brand Id boş geçilemez")
    @Positive(message = "Brand Id 0'dan küçük olamaz")
    private int brandId;

}
