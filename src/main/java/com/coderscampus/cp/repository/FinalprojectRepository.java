package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Finalproject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalprojectRepository extends JpaRepository<Finalproject, Long> {

    @Query("SELECT f FROM Finalproject f JOIN FETCH f.student")
    List<Finalproject> findAllWithStudents();
}





