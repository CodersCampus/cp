package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.MyTableRowThing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyTableRowThingRepository extends JpaRepository<MyTableRowThing, Long> {
}


