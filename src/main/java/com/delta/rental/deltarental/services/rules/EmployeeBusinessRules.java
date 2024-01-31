package com.delta.rental.deltarental.services.rules;

import com.delta.rental.deltarental.entities.concretes.Employee;
import com.delta.rental.deltarental.repositories.EmployeeRepository;
import com.delta.rental.deltarental.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeBusinessRules {
    private final EmployeeRepository employeeRepository;

    //DB içerisinde Employee id' ye sahip employee olup olmama durumu kontrolü
    public Employee checkByEmployeeId(int id){
        if(!(employeeRepository.existsById(id))){
            throw new RuntimeException(id + Messages.EmployeeMessages.EMPLOYEE_NOT_FOUND);
        }
        return employeeRepository.findById(id).orElseThrow();
    }




}
