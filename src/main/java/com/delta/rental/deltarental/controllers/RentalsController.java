package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.RentalService;
import com.delta.rental.deltarental.services.dtos.requests.brand.AddBrandRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.rental.AddRentalRequest;
import com.delta.rental.deltarental.services.dtos.requests.rental.UpdateRentalRequest;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelListResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalListResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/rentals")
@CrossOrigin
public class RentalsController {
    private final RentalService rentalService;

    @GetMapping("{id}")
    public GetRentalResponse getById(int id){
        return rentalService.getById(id);
    }
    @GetMapping("/getAll")
    public List<GetRentalListResponse> getAll(){
        return rentalService.getAll();
    }
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddRentalRequest addRentalRequest) {
        rentalService.add(addRentalRequest);
    }

    @PutMapping()
    public void update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest){
        rentalService.update(updateRentalRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        rentalService.delete(id);
    }
}

