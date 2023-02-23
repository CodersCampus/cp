package com.coderscampus.springwise.repository;

import com.coderscampus.dryRunH2.domain.SpringProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringProjectRepository extends JpaRepository<SpringProject,Long> {
}
