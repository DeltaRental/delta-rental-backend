package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.CustomerService;
import com.delta.rental.deltarental.services.dtos.requests.customer.AddCustomerRequest;
import com.delta.rental.deltarental.services.dtos.requests.customer.UpdateCustomerRequest;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerListResponse;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/customers")
@CrossOrigin
public class CustomersController {

    private CustomerService customerService;

    @GetMapping("/getAll")
    public List<GetCustomerListResponse> getAll(){
        return customerService.getAll();
    }

    @GetMapping("{id}")
    public GetCustomerResponse getById(int id){
        return customerService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCustomerRequest addCustomerRequest){
        customerService.add(addCustomerRequest);
    }

    @PutMapping()
    public void update(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
        customerService.update(updateCustomerRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        customerService.delete(id);
    }
}
