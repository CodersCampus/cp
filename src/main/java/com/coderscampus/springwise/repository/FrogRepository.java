package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.springwise.domain.Frog;

public interface FrogRepository extends JpaRepository<Frog, Long>{

}
