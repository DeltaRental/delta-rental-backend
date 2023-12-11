package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {
}
