package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.practice.domain.Airplane;
import com.coderscampus.springwise.domain.CodingCategory;

@Repository
public interface CodingCategoryRepository extends JpaRepository<CodingCategory, Long> {

}
