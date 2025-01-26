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

    @Query("SELECT c FROM Checkin c " +
            "JOIN c.student s " +
            "WHERE s.name = :name " +
            "AND c.blocker = true " +
            "AND c.blockerDescription = :blockerDescription " +
            "AND c.date = :date")
    List<Checkin> findCheckinsByStudentNameAndBlockerAndDate(
            @Param("name") String name,
            @Param("blockerDescription") String blockerDescription,
            @Param("date") LocalDate date);

    @Query("SELECT new com.coderscampus.cp.dto.CheckinDTO(s.name, a.issueNumber, c.date) " +
            "FROM ActivityLog a " +
            "JOIN a.checkin c " +
            "JOIN c.student s " +
            "ORDER BY c.date DESC")
    List<CheckinDTO> findCodersActivities();
}


