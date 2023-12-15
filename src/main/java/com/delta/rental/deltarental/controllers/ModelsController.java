package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.ModelService;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/models")
public class ModelsController {
    private final ModelService modelService;

    @GetMapping("{id}")
    public GetModelResponse getById(int id){
        return modelService.getById(id);
    }
}
