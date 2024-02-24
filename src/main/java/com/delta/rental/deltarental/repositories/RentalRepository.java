package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.concretes.Rental;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Integer> {

    @Query(nativeQuery = true,value = "select r.* from rentals r inner join users u on r.user_id = u.id where u.id=?1 order by r.start_date desc")
    List<Rental> filterRentalByUsers(int id);
}
