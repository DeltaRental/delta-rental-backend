package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch,Integer> {
    boolean existsByName(String name);
}
