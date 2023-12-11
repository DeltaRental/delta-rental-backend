package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
}
