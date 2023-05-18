package com.coderscampus.springwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Frog;
import com.coderscampus.springwise.repository.FrogRepository;

@Service
public class FrogService {

	@Autowired
	private FrogRepository frogRepo;

	public Frog save(Frog frog) {
		return frogRepo.save(frog);
	}

	public List<Frog> findAll() {

		return frogRepo.findAll();
	}

	public Frog findById(Long id) {
		return frogRepo.findById(id).get();
	}

	public void delete(Frog frog) {
		frogRepo.delete(frog);
	}

}
