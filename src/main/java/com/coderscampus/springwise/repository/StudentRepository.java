package com.coderscampus.springwise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.springwise.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
List<Student> findByUid(String uid);
}
