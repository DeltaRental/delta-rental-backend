package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.concretes.Car;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    boolean existsByPlate(String plate);

    boolean existsById(int id);

    @Query(nativeQuery = true, value = "select c.* from cars c inner join branches b on c.branch_id = b.id where c.car_state = 'AVAILABLE' and b.name = ?1 AND c.id not in (select c.id from cars c left join rentals r on c.id = r.car_id inner join branches b on c.branch_id = b.id where c.car_state = 'AVAILABLE' and b.name = ?1 AND ( ?3 between r.start_date and r.end_date or ?2 between r.start_date and r.end_date))")
    List<Car> filterCars(String startLocation,LocalDate endDate,LocalDate startDate);

    @Query(nativeQuery = true,value = "select * from cars car Where car.plate LIKE %:plate%")
    List<Car> searchPlate(String plate);

}

