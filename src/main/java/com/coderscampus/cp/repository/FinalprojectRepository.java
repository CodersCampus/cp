package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Finalproject;
import com.coderscampus.cp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinalprojectRepository extends JpaRepository<Finalproject, Long> {

    @Query("SELECT f FROM Finalproject f JOIN FETCH f.student")
    List<Finalproject> findAllWithStudents();

    List<Finalproject> findByStudent(Student student);
    List<Finalproject> findByStudentUid(String uid);
    Optional<Finalproject> findByIdAndStudentUid(Long id, String uid);
}




