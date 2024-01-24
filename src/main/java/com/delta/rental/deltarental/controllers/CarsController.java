package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/cars")
@CrossOrigin
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
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCarRequest addCarRequest) {
        carService.add(addCarRequest);
    }

    @PutMapping()
    public void update(@RequestBody @Valid UpdateCarRequest updateCarRequest){
        carService.update(updateCarRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        carService.delete(id);
    }

    //@GetMapping("/getCarAvailability")
    //public List<GetCarListResponse> getCarAvailability(){
    //    return carService.getAllByIsStatusTrue();
    //}
}
