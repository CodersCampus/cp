package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
