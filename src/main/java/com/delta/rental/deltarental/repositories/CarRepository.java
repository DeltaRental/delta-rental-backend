package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.Car;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    boolean existsByPlate(String plate);

    boolean existsById(int id);

    boolean existsByModelId(int id);

    boolean existsByColorId(int id);

    List<Car> findAllByStatusTrue();

}

