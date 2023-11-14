package com.coderscampus.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.practice.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
