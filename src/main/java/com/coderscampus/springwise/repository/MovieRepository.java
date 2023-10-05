package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.springwise.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

}
