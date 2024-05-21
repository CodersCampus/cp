package com.coderscampus.cp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.cp.domain.SpringProject;
import com.coderscampus.cp.repository.SpringProjectRepository;

@Service
public class SpringProjectService {

    @Autowired
    SpringProjectRepository springRepo;

    public List<SpringProject> findAll() {
        return springRepo.findAll();
    }

    public Optional<SpringProject> findById(Long springProjectId) {
        return springRepo.findById(springProjectId);
    }
}
 