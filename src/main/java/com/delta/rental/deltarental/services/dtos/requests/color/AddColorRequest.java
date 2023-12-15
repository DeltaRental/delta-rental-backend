package com.delta.rental.deltarental.services.dtos.requests.color;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddColorRequest {
    @NotBlank(message = "Renk boş olamaz!")
    @Length(min = 2,message = "Girilen renk en az 2 harfli olmalıdır.")
    private String name;

}
