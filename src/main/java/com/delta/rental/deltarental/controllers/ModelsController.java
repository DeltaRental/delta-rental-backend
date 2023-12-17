package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.ModelService;
import com.delta.rental.deltarental.services.dtos.requests.color.AddColorRequest;
import com.delta.rental.deltarental.services.dtos.requests.color.UpdateColorRequest;
import com.delta.rental.deltarental.services.dtos.requests.model.AddModelRequest;
import com.delta.rental.deltarental.services.dtos.requests.model.UpdateModelRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import com.delta.rental.deltarental.services.dtos.responses.color.GetColorListResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelListResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/models")
public class ModelsController {
    private final ModelService modelService;

    @GetMapping("{id}")
    public GetModelResponse getById(int id){
        return modelService.getById(id);
    }

    @GetMapping("/getAll")
    public List<GetModelListResponse> getAll(){
        return modelService.getAll();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddModelRequest addModelRequest) {
        modelService.add(addModelRequest);
    }

    @PutMapping()
    public void update(@RequestBody @Valid UpdateModelRequest updateModelRequest){
        modelService.update(updateModelRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        modelService.delete(id);
    }
}
