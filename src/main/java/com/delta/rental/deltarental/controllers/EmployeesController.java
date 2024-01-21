package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.EmployeeService;
import com.delta.rental.deltarental.services.dtos.requests.employee.AddEmployeeRequest;
import com.delta.rental.deltarental.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeListResponse;
import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
@CrossOrigin
public class EmployeesController {
    private final EmployeeService employeeService;

    @GetMapping("{id}")
    public GetEmployeeResponse getById(int id){
        return employeeService.getById(id);
    }

    @GetMapping("/getAll")
    public List<GetEmployeeListResponse> getAll(){
        return employeeService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddEmployeeRequest addEmployeeRequest) {
        employeeService.add(addEmployeeRequest);
    }

    @PutMapping()
    public void update(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest){
        employeeService.update(updateEmployeeRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        employeeService.delete(id);
    }
}
