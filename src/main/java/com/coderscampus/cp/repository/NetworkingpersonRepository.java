package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Networkingperson;
import com.coderscampus.cp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkingpersonRepository extends JpaRepository<Networkingperson, Long> {
    List<Networkingperson> findByStudent(Student student);
}


