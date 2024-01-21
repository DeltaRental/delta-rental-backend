package com.delta.rental.deltarental.services.dtos.requests.car;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {
    @NotNull(message = "Kilometre boş geçilemez.")
    @Positive(message = "Kilometre 0'dan küçük olamaz.")
    private double kilometer;

    @NotNull(message = "Yıl boş geçilemez")
    @Min(value = 2005, message = "Yıl 2005'den küçük olamaz.")
    @Max(value = 2024, message = "Yıl 2024'den büyük olamaz.")
    private int year;

    @Positive(message = "Günlük kiralama ücreti 0'dan küçük olamaz.")
    private double dailyPrice;

    //@Pattern(regexp = "(?i)^[0-9]{2}[A-Z]{1,3}[0-9]{1,4}$", message = "Boşluksuz ve geçerli bir plaka giriniz.")
    @Pattern(regexp = "(?i)^\\s*[0-9]{2}\\s*[A-Z]{1,3}\\s*[0-9]{1,4}\\s*$", message = "geçerli bir plaka giriniz.")
    private String plate;

    @Positive(message = "Model id 0' dan küçük bir değer olamaz.")
    private int modelId;

    @Positive(message = "Color id 0' dan küçük bir değer olamaz.")
    private int colorId;


}
