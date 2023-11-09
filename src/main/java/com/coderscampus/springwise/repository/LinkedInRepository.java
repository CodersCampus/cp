package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.springwise.domain.Frog;
import com.coderscampus.springwise.domain.GitHub;
import com.coderscampus.springwise.domain.LinkedIn;

@Repository
public interface LinkedInRepository extends JpaRepository<LinkedIn, Long>{

}


