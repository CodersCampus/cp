package com.coderscampus.springwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Airplane;
import com.coderscampus.springwise.repository.AirplaneRepository;

@Service
public class AirplaneService {

	@Autowired
	private AirplaneRepository airplaneRepo;

	public Airplane save(Airplane airplane) {
		return airplaneRepo.save(airplane);
	}

	public List<Airplane> findAll() {

		return airplaneRepo.findAll();
	}

	public Airplane findById(Long id) {
		return airplaneRepo.findById(id).get();
	}

	public void delete(Airplane airplane) {
		airplaneRepo.delete(airplane);
	}

}
