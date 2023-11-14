package com.coderscampus.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.practice.domain.Frog;

@Repository
public interface FrogRepository extends JpaRepository<Frog, Long>{

}
