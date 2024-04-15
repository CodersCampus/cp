package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Student findByUid(String uid);
}
