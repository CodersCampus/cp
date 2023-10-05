package com.coderscampus.springwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Movie;
import com.coderscampus.springwise.repository.MovieRepository;

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
