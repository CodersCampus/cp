package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.springwise.domain.Dog;

public interface DogRepository extends JpaRepository<Dog, Long>{

}
