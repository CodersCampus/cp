package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Barfoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarfooRepository extends JpaRepository<Barfoo, Long> {
}


