package com.coderscampus.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.practice.domain.Truck;

public interface TruckRepository extends JpaRepository<Truck, Long> {

}
