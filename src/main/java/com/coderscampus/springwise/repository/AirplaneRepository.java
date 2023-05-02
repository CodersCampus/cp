package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.springwise.domain.Airplane;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long>{

}
