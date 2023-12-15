package com.delta.rental.deltarental.services.dtos.requests.color;


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
public class UpdateColorRequest {

    @NotNull(message = "Id boş geçilemez")
    @Positive(message = "Id 0'dan küçük olamaz")
    private int id;

    @NotBlank(message = "Renk boş olamaz!")
    @Length(min = 2,message = "Girilen renk en az 2 harfli olmalıdır.")
    private String name;
}
