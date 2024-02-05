package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.concretes.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByName(String fileName);
}
