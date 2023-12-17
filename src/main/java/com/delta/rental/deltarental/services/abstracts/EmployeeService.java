package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;

public interface EmployeeService {
    GetEmployeeResponse getById(int id);
}
