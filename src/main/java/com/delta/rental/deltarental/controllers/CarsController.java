package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/cars")
public class CarsController {

    private final CarService carService;

    @GetMapping("{id}")
    public GetCarResponse getById(int id){
        return carService.getById(id);
    }

    @GetMapping("/getAll")
    public List<GetCarListResponse> getAll(){
        return carService.getAll();
    }

    @PostMapping
    public void add(@RequestBody @Valid AddCarRequest addCarRequest) {
        carService.add(addCarRequest);
    }

    @PutMapping("{id}")
    public void update(@RequestBody UpdateCarRequest updateCarRequest,@PathVariable int id){
        carService.update(updateCarRequest,id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        carService.delete(id);
    }
}
