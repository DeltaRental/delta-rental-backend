package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.ColorService;
import com.delta.rental.deltarental.services.abstracts.ModelService;
import com.delta.rental.deltarental.services.dtos.responses.color.GetColorResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/colors")
public class ColorsController {
    private final ColorService colorService;

    @GetMapping("{id}")
    public GetColorResponse getById(int id){
        return colorService.getById(id);
    }
}
