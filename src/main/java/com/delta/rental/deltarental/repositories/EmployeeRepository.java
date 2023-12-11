package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
