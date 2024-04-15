package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.SpringProject;
import com.coderscampus.cp.repository.SpringProjectRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringProjectService {

  @Autowired SpringProjectRepository springRepo;

  public List<SpringProject> findAll() {
    return springRepo.findAll();
  }

  public Optional<SpringProject> findById(Long springProjectId) {
    return springRepo.findById(springProjectId);
  }
}
