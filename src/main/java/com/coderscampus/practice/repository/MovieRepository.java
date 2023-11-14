package com.coderscampus.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.practice.domain.Dog;
import com.coderscampus.practice.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

}
