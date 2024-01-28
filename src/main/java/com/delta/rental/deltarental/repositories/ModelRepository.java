package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model,Integer> {
    boolean existsByName(String name);
}
