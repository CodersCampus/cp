package com.coderscampus.springwise.web;

import com.coderscampus.springwise.domain.Movie;
import com.coderscampus.springwise.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
// This is same mapping as Movie Home page-->left here just in case.(may need deleted)
//	@GetMapping("/read")
//	public String read(ModelMap model) {
//		List<Movie> movies = movieService.findAll();
//		model.put("movies", movies);
//		return "movie/read";
//	}
	
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
