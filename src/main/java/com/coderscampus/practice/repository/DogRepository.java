package com.coderscampus.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.practice.domain.Dog;

public interface DogRepository extends JpaRepository<Dog, Long>{

}
