package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.employee.AddEmployeeRequest;
import com.delta.rental.deltarental.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeListResponse;
import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeResponse;

import java.util.List;

public interface EmployeeService {
    GetEmployeeResponse getById(int id);
    List<GetEmployeeListResponse> getAll();
    void add (AddEmployeeRequest addEmployeeRequest);
    void update(UpdateEmployeeRequest updateEmployeeRequest);
    void delete(int id);
}
