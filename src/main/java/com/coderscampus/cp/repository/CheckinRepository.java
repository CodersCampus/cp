package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.dto.CheckinDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {
    List<Checkin> findByUid(String uid);

    @Query("SELECT c FROM Checkin c JOIN c.student s WHERE c.blocker = true ORDER BY c.date DESC")
    List<Checkin> findAllBlockers();


    @Query("SELECT new com.coderscampus.cp.dto.CheckinDTO(s.name, a.issueNumber, c.date, a.role) " +
            "FROM ActivityLog a " +
            "JOIN a.checkin c " +
            "JOIN c.student s " +
            "WHERE a.role = 'CODER' " +
            "ORDER BY c.date DESC")
    List<CheckinDTO> findCodersActivities();

    List<Checkin> uid(String uid);
}


