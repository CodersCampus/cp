package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.springwise.domain.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>{

}
