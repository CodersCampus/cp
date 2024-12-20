package com.coderscampus.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.cp.domain.Work;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>{
    List<Work> findAllByOrderByDateDesc();

}
