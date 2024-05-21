package com.coderscampus.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.cp.domain.CodingCategory;

@Repository
public interface CodingCategoryRepository extends JpaRepository<CodingCategory, Long> {

}
