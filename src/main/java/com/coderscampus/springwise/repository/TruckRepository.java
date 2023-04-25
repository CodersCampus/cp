package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.springwise.domain.Truck;

public interface TruckRepository extends JpaRepository<Truck, Long> {

}
