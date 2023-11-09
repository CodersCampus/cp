package com.coderscampus.springwise.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.LinkedIn;
import com.coderscampus.springwise.repository.LinkedInRepository;

@Service
public class LinkedInService {
	@Autowired
	private LinkedInRepository linkedInRepo;

	public LinkedIn save(LinkedIn linkedIn) {
		return linkedInRepo.save(linkedIn);
	}

	public List<LinkedIn> findAll() {

		return linkedInRepo.findAll();
	}

	public LinkedIn findById(Long id) {
		return linkedInRepo.findById(id).get();
	}

	public void delete(LinkedIn linkedIn) {
		linkedInRepo.delete(linkedIn);
	}


}
