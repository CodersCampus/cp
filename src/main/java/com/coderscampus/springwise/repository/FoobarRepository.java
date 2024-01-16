package com.coderscampus.springwise.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderscampus.springwise.domain.Foobar;

@Repository
public interface FoobarRepository extends JpaRepository<Foobar, Long> {
}


