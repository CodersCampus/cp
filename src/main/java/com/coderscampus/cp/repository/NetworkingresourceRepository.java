package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Networkingresource;
import com.coderscampus.cp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkingresourceRepository extends JpaRepository<Networkingresource, Long> {
    List<Networkingresource> findByStudent(Student student);
}


