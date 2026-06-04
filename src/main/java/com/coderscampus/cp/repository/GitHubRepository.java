package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GitHubRepository extends JpaRepository<GitHub, Long> {
    List<GitHub> findByStudent(Student student);
    List<GitHub> findByStudentUid(String uid);
    boolean existsByStudentUid(String uid);
    Optional<GitHub> findByIdAndStudentUid(Long id, String uid);
}

