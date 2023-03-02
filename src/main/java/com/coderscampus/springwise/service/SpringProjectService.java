package com.coderscampus.springwise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.SpringProject;
import com.coderscampus.springwise.repository.SpringProjectRepository;

@Service
public class SpringProjectService {

	@Autowired
	SpringProjectRepository springRepo;
	
	public List<SpringProject> findAll() {
		return springRepo.findAll();
	}
	
	public  Optional<SpringProject> findById(Long springProjectId) {
		return springRepo.findById(springProjectId);
	}
			
	
}
