package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Customer;
import com.delta.rental.deltarental.entities.Employee;
import com.delta.rental.deltarental.repositories.EmployeeRepository;
import com.delta.rental.deltarental.services.abstracts.EmployeeService;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerResponse;
import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeManager implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapperService modelMapperService;
    @Override
    public GetEmployeeResponse getById(int id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException(id + " nolu id' ye sahip çalışan bulunmamaktadır.");
        });
        GetEmployeeResponse employeeResponse = modelMapperService.forResponse().map(employee, GetEmployeeResponse.class);
        return employeeResponse;
    }
}
