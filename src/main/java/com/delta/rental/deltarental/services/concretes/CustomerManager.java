package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Brand;
import com.delta.rental.deltarental.entities.Customer;
import com.delta.rental.deltarental.repositories.CustomerRepository;
import com.delta.rental.deltarental.services.abstracts.CustomerService;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandResponse;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerResponse;
import com.delta.rental.deltarental.services.rules.CustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerBusinessRules customerBusinessRules;

    @Override
    public GetCustomerResponse getById(int id) {

        Customer customer = customerBusinessRules.checkByCustomerId(id);
        GetCustomerResponse customerResponse = modelMapperService.forResponse().map(customer, GetCustomerResponse.class);
        return customerResponse;
    }
}
