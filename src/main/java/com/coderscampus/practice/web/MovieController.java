package com.coderscampus.practice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.practice.domain.Movie;
import com.coderscampus.practice.service.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/")
	public String home(ModelMap model) {
		List<Movie> movies = movieService.findAll();
		model.put("movies", movies);
		return "movie/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		Movie movie = new Movie();
		model.put("movie", movie);
		return "movie/create";
	}

	@PostMapping("/create")
	public String create(Movie movie) {
		movieService.save(movie);
		return "redirect:/movie/";
	}

	
	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Movie movie = movieService.findById(id);
		model.put("movie", movie);
		return "movie/update";
	}
	
	@PostMapping("/update")
	public String update(Movie movie) {
		movieService.save(movie);
		return "redirect:/movie/";
	}
	
	@PostMapping("/delete")
	public String delete(Movie movie) {
		movieService.delete(movie);
		return "redirect:/movie/";
	}
}
