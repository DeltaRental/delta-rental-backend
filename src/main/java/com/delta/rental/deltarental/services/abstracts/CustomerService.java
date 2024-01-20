package com.delta.rental.deltarental.services.abstracts;


import com.delta.rental.deltarental.services.dtos.requests.customer.AddCustomerRequest;
import com.delta.rental.deltarental.services.dtos.requests.customer.UpdateCustomerRequest;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerListResponse;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;

import java.util.List;

public interface CustomerService {
    GetCustomerResponse getById(int id);
    List<GetCustomerListResponse> getAll();
    void add (AddCustomerRequest addCustomerRequest);
    void update(UpdateCustomerRequest updateCustomerRequest);
    void delete(int id);

}
