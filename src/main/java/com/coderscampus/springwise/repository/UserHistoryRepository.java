package com.coderscampus.springwise.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.springwise.domain.UserHistory;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}


