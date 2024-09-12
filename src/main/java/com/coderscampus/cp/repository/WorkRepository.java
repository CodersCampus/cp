package com.coderscampus.cp.repository;

import com.coderscampus.cp.dto.WorkLogDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coderscampus.cp.domain.Work;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>{

    @Query("SELECT w.studentName, COUNT(w), SUM(w.numberMinutes) FROM Work w WHERE w.date >= DATE_SUB(NOW(), INTERVAL 1 WEEK)\n" +
            "GROUP BY \n" +
            "    w.studentName\n" +
            "ORDER BY \n" +
            "    w.studentName ASC")
    public List<WorkLogDTO> getWeeklyWorkLog();


}
