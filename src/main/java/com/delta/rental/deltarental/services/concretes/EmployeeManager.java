package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Customer;
import com.delta.rental.deltarental.entities.Employee;
import com.delta.rental.deltarental.repositories.EmployeeRepository;
import com.delta.rental.deltarental.services.abstracts.EmployeeService;
import com.delta.rental.deltarental.services.dtos.responses.customer.GetCustomerResponse;
import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeResponse;
import com.delta.rental.deltarental.services.rules.EmployeeBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeManager implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapperService modelMapperService;
    private final EmployeeBusinessRules employeeBusinessRules;

    @Override
    public GetEmployeeResponse getById(int id) {

        Employee employee = employeeBusinessRules.checkByEmployeeId(id);
        GetEmployeeResponse employeeResponse = modelMapperService.forResponse().map(employee, GetEmployeeResponse.class);
        return employeeResponse;
    }
}
