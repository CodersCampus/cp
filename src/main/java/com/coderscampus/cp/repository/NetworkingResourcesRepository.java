package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Foobar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkingResourcesRepository extends JpaRepository<Foobar, Long> {
}


