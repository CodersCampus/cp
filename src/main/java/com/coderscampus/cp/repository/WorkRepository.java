package com.coderscampus.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coderscampus.cp.domain.Work;

import java.time.Instant;
import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>{
    List<Work> findAllByOrderByDateDesc();

    @Query(value = """
    SELECT SUM(w.number_minutes)
    FROM work w
    WHERE w.student_name = :studentName
      AND w.date >= :startDate
      AND w.date < :endDate
""", nativeQuery = true)
    Integer getAggregateMinutes(
            @Param("studentName") String studentName,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate);
}
