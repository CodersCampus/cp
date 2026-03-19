package com.coderscampus.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.domain.Student;

import java.util.List;

@Repository
public interface LinkedInRepository extends JpaRepository<LinkedIn, Long> {
    List<LinkedIn> findByStudent(Student student);
}

