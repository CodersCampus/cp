package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Networkingperson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkingpersonRepository extends JpaRepository<Networkingperson, Long> {
}


