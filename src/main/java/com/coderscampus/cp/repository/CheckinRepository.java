package com.coderscampus.cp.repository;
import com.coderscampus.cp.domain.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {
    @Query("SELECT c FROM Checkin c WHERE c.student.id = :studentId AND c.endTime IS NULL")
    Optional<Checkin> findByStudentIdAndCheckoutTimeIsNull(Long studentId);
}


