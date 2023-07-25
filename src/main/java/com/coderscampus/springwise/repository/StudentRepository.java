package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.springwise.domain.Dog;
import com.coderscampus.springwise.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
