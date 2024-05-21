package com.coderscampus.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.cp.domain.Foobar;

@Repository
public interface FoobarRepository extends JpaRepository<Foobar, Long> {
}


