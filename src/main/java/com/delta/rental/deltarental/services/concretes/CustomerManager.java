package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Brand;
import com.delta.rental.deltarental.entities.Customer;
import com.delta.rental.deltarental.repositories.CustomerRepository;
import com.delta.rental.deltarental.services.abstracts.CustomerService;
import com.delta.rental.deltarental.services.dtos.requests.customer.AddCustomerRequest;
import com.delta.rental.deltarental.services.dtos.requests.customer.UpdateCustomerRequest;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandResponse;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerListResponse;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerResponse;
import com.delta.rental.deltarental.services.rules.CustomerBusinessRules;
import com.delta.rental.deltarental.services.rules.RentalBusinessRules;
import com.delta.rental.deltarental.services.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerBusinessRules customerBusinessRules;
    private final UserBusinessRules userBusinessRules;


    @Override
    public GetCustomerResponse getById(int id) {

        Customer customer = customerBusinessRules.checkByCustomerId(id);
        GetCustomerResponse customerResponse = modelMapperService.forResponse().map(customer, GetCustomerResponse.class);
        return customerResponse;
    }

    @Override
    public List<GetCustomerListResponse> getAll() {
        List <Customer> customerList = customerRepository.findAll();

        List<GetCustomerListResponse> customerResponse = customerList.stream()
                .map(customer -> this.modelMapperService.forResponse()
                        .map(customer, GetCustomerListResponse.class)).collect(Collectors.toList());
        return customerResponse;
    }

    @Override
    public void add(AddCustomerRequest addCustomerRequest) {
        customerBusinessRules.checkByNationalityId(addCustomerRequest.getNationalityId());
        userBusinessRules.checkByUserId(addCustomerRequest.getUserId());

        Customer customer = this.modelMapperService.forRequest()
                .map(addCustomerRequest, Customer.class);

        customer.setNationalityId(customer.getNationalityId().trim().replaceAll("\\s", ""));

        customerRepository.save(customer);
    }

    @Override
    public void update(UpdateCustomerRequest updateCustomerRequest) {
        customerBusinessRules.checkByNationalityId(updateCustomerRequest.getNationalityId());
        customerBusinessRules.checkByCustomerId(updateCustomerRequest.getId());
        userBusinessRules.checkByUserId(updateCustomerRequest.getUserId());

        Customer customer = this.modelMapperService.forRequest()
                .map(updateCustomerRequest, Customer.class);

        customer.setNationalityId(customer.getNationalityId().trim().replaceAll("\\s", ""));

        customerRepository.save(customer);
    }

    @Override
    public void delete(int id) {
        customerBusinessRules.checkByCustomerId(id);
        customerRepository.deleteById(id);
    }


}
