package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.BrandService;
import com.delta.rental.deltarental.services.dtos.requests.brand.AddBrandRequest;
import com.delta.rental.deltarental.services.dtos.requests.brand.UpdateBrandRequest;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandListResponse;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/brands")
@CrossOrigin
public class BrandsController {
    private BrandService brandService;


    @GetMapping("{id}")
    public GetBrandResponse getById(int id){
        return brandService.getById(id);
    }

    @GetMapping("/getAll")
    public List<GetBrandListResponse> getAll(){
        return brandService.getAll();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddBrandRequest addBrandRequest) {
        brandService.add(addBrandRequest);
    }

    @PutMapping()
    public void update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest){
        brandService.update(updateBrandRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        brandService.delete(id);
    }

}
