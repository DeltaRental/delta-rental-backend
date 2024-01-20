package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByNationalityId (String nationalityId);


}
