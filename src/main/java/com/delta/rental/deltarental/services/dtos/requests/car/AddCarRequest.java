package com.delta.rental.deltarental.services.dtos.requests.car;

import com.delta.rental.deltarental.enums.CarState;
import com.delta.rental.deltarental.enums.FuelType;
import com.delta.rental.deltarental.enums.GearType;
import com.delta.rental.deltarental.services.constants.Messages;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {
    @NotNull(message = Messages.CarMessages.KILOMETER_NOT_NULL)
    @Positive(message = Messages.CarMessages.KILOMETER_NOT_NEGATIVE)
    private double kilometer;

    @NotNull(message = Messages.CarMessages.YEAR_NOT_NULL)
    @Min(value = 2005, message = Messages.CarMessages.YEAR_NOT_LESS_THAN_TWO_THOUSAND_FIVE)
    @Max(value = 2024, message = Messages.CarMessages.YEAR_NOT_GREATER_THAN_TWO_THOUSAND_TWENTY_FOUR)
    private int year;

    @Positive(message = Messages.CarMessages.DAILY_PRICE_NOT_NEGATIVE)
    private double dailyPrice;

    //@Pattern(regexp = "(?i)^[0-9]{2}[A-Z]{1,3}[0-9]{1,4}$", message = "Boşluksuz ve geçerli bir plaka giriniz.")
    @Pattern(regexp = "(?i)^\\s*[0-9]{2}\\s*[A-Z]{1,3}\\s*[0-9]{1,4}\\s*$", message = Messages.CarMessages.ENTER_VALID_PLATE)
    private String plate;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int modelId;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int colorId;

    @Positive(message = Messages.IdMessages.ID_NOT_NEGATIVE)
    private int branchId;

    private CarState carState;

    private GearType gearType;

    private FuelType fuelType;

    private String imageUrl;




}
