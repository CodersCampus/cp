package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUid(String uid);

    boolean existsByUid(String uid);


    @Query("SELECT DISTINCT c.student.id FROM Checkin c WHERE c.date >= :cutoffDate")
    List<Long> findActiveStudentIds(@Param("cutoffDate") Instant cutoffDate);

}
