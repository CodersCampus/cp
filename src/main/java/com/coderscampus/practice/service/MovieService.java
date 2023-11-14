package com.coderscampus.practice.service;

import com.coderscampus.practice.domain.Movie;
import com.coderscampus.practice.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepo;
	
	public Movie save(Movie movie) {
		return movieRepo.save(movie);
	}

	public List<Movie> findAll() {

		return movieRepo.findAll();
	}

	public Movie findById(Long id) {
		return movieRepo.findById(id).get();
	}

	public void delete(Movie movie) {
		movieRepo.delete(movie);
	}

}
