package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;

public interface CustomerService {
    GetCustomerResponse getById(int id);
}
