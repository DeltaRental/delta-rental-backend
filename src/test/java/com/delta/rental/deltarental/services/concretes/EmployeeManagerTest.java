
package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.entities.concretes.Employee;
import com.delta.rental.deltarental.repositories.EmployeeRepository;
import com.delta.rental.deltarental.services.dtos.requests.employee.AddEmployeeRequest;
import com.delta.rental.deltarental.services.dtos.responses.employee.GetEmployeeListResponse;
import com.delta.rental.deltarental.services.rules.EmployeeBusinessRules;
import com.delta.rental.deltarental.services.rules.UserBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeManagerTest {



    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeBusinessRules employeeBusinessRules;

    @Mock
    private UserBusinessRules userBusinessRules;

    @Mock
    private Employee employee;

    @InjectMocks
    private EmployeeManager employeeManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());
        employeeList.add(new Employee());
        when(employeeRepository.findAll()).thenReturn(employeeList);

        // Act
        List<GetEmployeeListResponse> result = employeeManager.getAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void testAddEmployee() {
        // Arrange
        AddEmployeeRequest addEmployeeRequest = new AddEmployeeRequest();
        addEmployeeRequest.setUserId(1);

        // Mock employeeRepository.save method
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        employeeManager.add(addEmployeeRequest);

        // Assert
        verify(userBusinessRules).checkByUserId(1);
        verify(employeeRepository).save(any(Employee.class));
    }

}
