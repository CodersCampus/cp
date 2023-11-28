package com.coderscampus.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.practice.domain.Dog;
import com.coderscampus.practice.repository.DogRepository;

@Service
public class DogService {
	
	@Autowired
	private DogRepository dogRepo;
	
	public Dog save(Dog dog) {
		return dogRepo.save(dog);
	}

	public List<Dog> findAll() {

		return dogRepo.findAll();
	}

	public Dog findById(Long id) {
		return dogRepo.findById(id).get();
	}

	public void delete(Dog dog) {
		dogRepo.delete(dog);
	}

}
