package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Integer> {
    boolean existsByName(String name);
}
