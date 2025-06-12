package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Networkingresource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkingresourceRepository extends JpaRepository<Networkingresource, Long> {
}


