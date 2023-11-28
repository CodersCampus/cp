package com.coderscampus.springwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.springwise.domain.YouTubeChannel;

@Repository
public interface YouTubeRepository extends JpaRepository<YouTubeChannel, Long>{

}


