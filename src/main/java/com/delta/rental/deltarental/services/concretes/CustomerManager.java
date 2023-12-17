package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Brand;
import com.delta.rental.deltarental.entities.Customer;
import com.delta.rental.deltarental.repositories.CustomerRepository;
import com.delta.rental.deltarental.services.abstracts.CustomerService;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandResponse;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;
    @Override
    public GetCustomerResponse getById(int id) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException(id + " nolu id' ye sahip müşteri bulunmamaktadır.");
        });
        GetCustomerResponse customerResponse = modelMapperService.forResponse().map(customer, GetCustomerResponse.class);
        return customerResponse;
    }
}
