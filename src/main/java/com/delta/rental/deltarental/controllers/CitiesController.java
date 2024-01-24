package com.delta.rental.deltarental.controllers;


import com.delta.rental.deltarental.services.abstracts.CityService;
import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.city.AddCityRequest;
import com.delta.rental.deltarental.services.dtos.requests.city.UpdateCityRequest;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import com.delta.rental.deltarental.services.dtos.responses.city.GetCityListResponse;
import com.delta.rental.deltarental.services.dtos.responses.city.GetCityResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/cities")
public class CitiesController {
    private CityService cityService;



    @GetMapping("{id}")
    public GetCityResponse getById(int id){
        return cityService.getById(id);
    }

    @GetMapping("/getAll")
    public List<GetCityListResponse> getAll(){
        return cityService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCityRequest addCityRequest) {
        cityService.add(addCityRequest);
    }

    @PutMapping()
    public void update(@RequestBody @Valid UpdateCityRequest updateCityRequest){
        cityService.update(updateCityRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        cityService.delete(id);
    }
}
