package com.example.demo.config.repositories;

import com.example.demo.config.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findCarById(Long id);
    Car findByName(String name);
}
