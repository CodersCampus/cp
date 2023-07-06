package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.springwise.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
