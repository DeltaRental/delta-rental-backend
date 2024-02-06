package com.delta.rental.deltarental.services.rules;
import com.delta.rental.deltarental.entities.concretes.Employee;
import com.delta.rental.deltarental.repositories.EmployeeRepository;
import com.delta.rental.deltarental.services.rules.EmployeeBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeBusinessRulesTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeBusinessRules employeeBusinessRules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckByEmployeeId_ExistingEmployee() {
        // Arrange
        int id = 1;
        Employee employee = new Employee();
        when(employeeRepository.existsById(id)).thenReturn(true);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // Act
        Employee result = employeeBusinessRules.checkByEmployeeId(id);

        // Assert
        assertNotNull(result);
        assertEquals(employee, result);
    }

    @Test
    void testCheckByEmployeeId_NonExistingEmployee() {
        // Arrange
        int id = 1;
        when(employeeRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            employeeBusinessRules.checkByEmployeeId(id);
        });
    }
    @Test
    void checkByEmployeeId() {

    }
}