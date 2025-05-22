package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Networkingresources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkingresourcesRepository extends JpaRepository<Networkingresources, Long> {
}


