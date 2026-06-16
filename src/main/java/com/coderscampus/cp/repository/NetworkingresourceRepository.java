package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Networkingresource;
import com.coderscampus.cp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NetworkingresourceRepository extends JpaRepository<Networkingresource, Long> {
    List<Networkingresource> findByStudent(Student student);
    List<Networkingresource> findByStudentUid(String uid);
    Optional<Networkingresource> findByIdAndStudentUid(Long id, String uid);
}

