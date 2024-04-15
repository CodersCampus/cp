package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.CodingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodingCategoryRepository extends JpaRepository<CodingCategory, Long> {}
