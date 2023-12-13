package com.delta.rental.deltarental.services.dtos.requests.car;

import com.delta.rental.deltarental.services.dtos.responses.model.GetModelListResponse;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {
    @NotBlank(message = "Kilometre boş geçilemez")
    @Positive(message = "Kilometre 0'dan küçük olamaz")
    private int kilometer;

    @NotBlank(message = "Yıl boş geçilemez")
    @Min(value = 2005, message = "Yıl 2005'den küçük olamaz")
    @Max(value = 2024, message = "Yıl 2024'den büyük olamaz")
    @Size(min = 4, max =4, message = "Yıl bilgisi 4 karakterden oluşmalı")
    private int year;

    @Positive(message = "Günlük kiralama ücreti 0'dan küçük olamaz")
    private double dailyPrice;

    @Pattern(regexp = "(?i)^[0-9]{2}[A-Z]{1,3}[0-9]{1,4}$", message = "Geçerli bir plaka giriniz.")
    private String plate;


}
