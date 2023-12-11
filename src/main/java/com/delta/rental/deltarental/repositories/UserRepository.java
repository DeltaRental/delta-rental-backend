package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
