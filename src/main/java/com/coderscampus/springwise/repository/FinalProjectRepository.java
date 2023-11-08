package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.springwise.domain.FinalProject;

@Repository
public interface FinalProjectRepository extends JpaRepository<FinalProject, Long>{

}
