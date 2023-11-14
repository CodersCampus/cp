package com.coderscampus.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.practice.domain.Airplane;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long>{

}
