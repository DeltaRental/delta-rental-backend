package com.delta.rental.deltarental.services.dtos.responses.car;

import com.delta.rental.deltarental.enums.CarState;
import com.delta.rental.deltarental.enums.FuelType;
import com.delta.rental.deltarental.enums.GearType;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchListResponse;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarListResponse {
    private int id;

    private double kilometer;

    private int year;

    private double dailyPrice;

    private String plate;

    private String modelName;

    private String colorName;

    private CarState carState;

    private GearType gearType;

    private FuelType fuelType;

    private GetBranchResponse branch;
}
