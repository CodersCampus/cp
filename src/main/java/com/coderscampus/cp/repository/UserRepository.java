package com.coderscampus.cp.repository;


import com.coderscampus.cp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUid(String uid);
    Optional<User> findByEmail(String email);

    boolean existsById(Long id);

    Boolean existsByUid(String uid);

    Boolean existsByEmail(String email);

}