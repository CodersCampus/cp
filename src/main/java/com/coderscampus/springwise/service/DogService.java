package com.coderscampus.springwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Dog;
import com.coderscampus.springwise.repository.DogRepository;

@Service
public class DogService {
	
	@Autowired
	private DogRepository dogRepo;
	
	public Dog saveDog(Dog dog) {
		return dogRepo.save(dog);
	}

	public List<Dog> findAll() {

		return dogRepo.findAll();
	}

	public Dog findById(Long id) {
		return dogRepo.findById(id).get();
	}

}
