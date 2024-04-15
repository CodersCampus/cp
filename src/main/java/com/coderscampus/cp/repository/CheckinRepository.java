package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Checkin;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {
  List<Checkin> findByUid(String uid);
}
