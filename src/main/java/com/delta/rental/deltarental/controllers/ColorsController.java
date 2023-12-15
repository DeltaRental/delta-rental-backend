package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.ColorService;
import com.delta.rental.deltarental.services.abstracts.ModelService;
import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.color.AddColorRequest;
import com.delta.rental.deltarental.services.dtos.requests.color.UpdateColorRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.color.GetColorListResponse;
import com.delta.rental.deltarental.services.dtos.responses.color.GetColorResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/colors")
public class ColorsController {
    private final ColorService colorService;

    @GetMapping("{id}")
    public GetColorResponse getById(int id){
        return colorService.getById(id);
    }

    @GetMapping("/getAll")
    public List<GetColorListResponse> getAll(){
        return colorService.getAll();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddColorRequest addColorRequest) {
        colorService.add(addColorRequest);
    }

    @PutMapping()
    public void update(@RequestBody @Valid UpdateColorRequest updateColorRequest){
        colorService.update(updateColorRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        colorService.delete(id);
    }



}
