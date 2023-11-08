package com.coderscampus.springwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.FinalProject;
import com.coderscampus.springwise.repository.FinalProjectRepository;

@Service
public class FinalProjectService {

	@Autowired
	private FinalProjectRepository finalProjectRepo;

	public FinalProject save(FinalProject finalProject) {
		return finalProjectRepo.save(finalProject);
	}

	public List<FinalProject> findAll() {

		return finalProjectRepo.findAll();
	}

	public FinalProject findById(Long id) {
		return finalProjectRepo.findById(id).get();
	}

	public void delete(FinalProject finalProject) {
		finalProjectRepo.delete(finalProject);
	}

}
