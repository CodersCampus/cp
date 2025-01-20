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

    @Query("SELECT new com.coderscampus.cp.dto.CheckinDTO(" +
            "c.id, c.date, c.blocker, c.blockerDescription, c.isSetup, c.available, a.comment) " +
            "FROM Checkin c " +
            "LEFT JOIN c.activityLogs a")
    List<CheckinDTO> findAllCheckinsWithActivityLogs();
}



