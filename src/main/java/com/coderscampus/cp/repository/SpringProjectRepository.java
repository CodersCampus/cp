package com.coderscampus.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.cp.domain.SpringProject;

@Repository
public interface SpringProjectRepository extends JpaRepository<SpringProject, Long> {
}
