package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/cars")
public class CarsController {

    private final CarService carService;

    @PostMapping
    public void add(@RequestBody @Valid AddCarRequest addCarRequest) {
        carService.add(addCarRequest);
    }
}
