package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    boolean existsByPlate(String plate);

    boolean existsById(int id);

    boolean existsByModelId(int id);

    boolean existsByColorId(int id);

    @Query (nativeQuery = true, value = "select c.* from cars c left join rentals r on c.id = r.car_id inner join branches b on c.branch_id = b.id where b.name = ?1 and (r.start_date > ?2 or r.end_date < ?3 or r.id is null) and c.car_state = 'AVAILABLE'")
    List<Car> filterCars (String startLocation, LocalDate endDate, LocalDate startDate);



}

