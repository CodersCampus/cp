package com.coderscampus.springwise.repository;

import com.coderscampus.springwise.domain.Dog;
import com.coderscampus.springwise.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

}
