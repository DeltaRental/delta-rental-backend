package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    boolean existsByName(String name);

}
