package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.SpringProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringProjectRepository extends JpaRepository<SpringProject, Long> {}
