package com.coderscampus.springwise.repository;
import com.coderscampus.springwise.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}


