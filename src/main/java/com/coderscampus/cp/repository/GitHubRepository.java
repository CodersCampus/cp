package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitHubRepository extends JpaRepository<GitHub, Long> {
}


