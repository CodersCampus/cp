package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Resume;
import com.coderscampus.cp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByStudent(Student student);
    List<Resume> findByStudentUid(String uid);
    boolean existsByStudentUid(String uid);
    Optional<Resume> findByIdAndStudentUid(Long id, String uid);
}

