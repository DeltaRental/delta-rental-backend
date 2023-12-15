package com.delta.rental.deltarental.services.dtos.requests.brand;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBrandRequest {
    @NotBlank(message = "Marka adı boş olamaz!")
    @Length(min = 2,message = "Girilen marka en az 2 harfli olmalıdır.")
    private String name;
}
