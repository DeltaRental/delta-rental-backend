package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {
    boolean existsByPlate(String plate);

    boolean existsById(int id);

    boolean existsByModelId(int id);

    boolean existsByColorId(int id);

    //List<Car> findAllByStatusTrue();

}

