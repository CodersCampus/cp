package com.coderscampus.springwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Foobar;
import com.coderscampus.springwise.repository.FoobarRepository;

@Service
public class FoobarService {

	@Autowired
	private FoobarRepository foobarRepo;

	public Foobar save(Foobar foobar) {
		
		return foobarRepo.save(foobar);
	}

	public List<Foobar> findAll() {

		return foobarRepo.findAll();
	
	}

	public Foobar findById(Long id) {
		return foobarRepo.findById(id).get();
	}

	public void delete(Foobar foobar) {
		foobarRepo.delete(foobar);
	}

}