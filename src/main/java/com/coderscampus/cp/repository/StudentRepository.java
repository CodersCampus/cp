package com.coderscampus.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.cp.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUid(String uid);
}
