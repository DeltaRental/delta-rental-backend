package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.concretes.Employee;
import com.delta.rental.deltarental.repositories.EmployeeRepository;
import com.delta.rental.deltarental.services.abstracts.EmployeeService;
import com.delta.rental.deltarental.services.dtos.requests.employee.AddEmployeeRequest;
import com.delta.rental.deltarental.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeListResponse;
import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeResponse;
import com.delta.rental.deltarental.services.rules.EmployeeBusinessRules;
import com.delta.rental.deltarental.services.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapperService modelMapperService;
    private EmployeeBusinessRules employeeBusinessRules;
    private UserBusinessRules userBusinessRules;

    @Override
    public GetEmployeeResponse getById(int id) {

        Employee employee = employeeBusinessRules.checkByEmployeeId(id);
        GetEmployeeResponse employeeResponse = modelMapperService.forResponse().map(employee, GetEmployeeResponse.class);
        return employeeResponse;
    }

    @Override
    public List<GetEmployeeListResponse> getAll() {
        List<Employee> employeeList = employeeRepository.findAll();

        List<GetEmployeeListResponse> employeeResponse = employeeList.stream()
                .map(employee ->this.modelMapperService.forResponse()
                        .map(employee, GetEmployeeListResponse.class)).collect(Collectors.toList());
        return employeeResponse;
    }

    @Override
    public void add(AddEmployeeRequest addEmployeeRequest) {
        userBusinessRules.checkByUserId(addEmployeeRequest.getUserId());

        Employee employee = this.modelMapperService.forRequest()
                .map(addEmployeeRequest, Employee.class);

        employeeRepository.save(employee);
    }

    @Override
    public void update(UpdateEmployeeRequest updateEmployeeRequest) {
        employeeBusinessRules.checkByEmployeeId(updateEmployeeRequest.getId());
        userBusinessRules.checkByUserId(updateEmployeeRequest.getUserId());

        Employee employee = this.modelMapperService.forRequest()
                .map(updateEmployeeRequest, Employee.class);

        employeeRepository.save(employee);
    }

    @Override
    public void delete(int id) {
        employeeBusinessRules.checkByEmployeeId(id);
        employeeRepository.deleteById(id);
    }
}
