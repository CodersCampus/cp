package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.springwise.domain.Frog;

@Repository
public interface FrogRepository extends JpaRepository<Frog, Long>{

}
