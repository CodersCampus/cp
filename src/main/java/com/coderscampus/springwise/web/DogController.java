package com.coderscampus.springwise.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.springwise.domain.Dog;
import com.coderscampus.springwise.service.DogService;

@Controller
@RequestMapping("/dog")
public class DogController {
	
	@Autowired
	private DogService dogService;
	
	@GetMapping("/")
	public String home(ModelMap model) {
		List<Dog> dogs = dogService.findAll();
		model.put("dogs", dogs);
		return "dog/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		Dog dog = new Dog();
		model.put("dog", dog);
		return "dog/create";
	}

	@PostMapping("/create")
	public String create(Dog dog) {
		dogService.save(dog);
		return "redirect:/dog/";
	}

	
	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Dog dog = dogService.findById(id);
		model.put("dog", dog);
		return "dog/update";
	}
	
	@PostMapping("/update")
	public String update(Dog dog) {
		dogService.save(dog);
		return "redirect:/dog/";
	}
	
	@PostMapping("/delete")
	public String delete(Dog dog) {
		dogService.delete(dog);
		return "redirect:/dog/";
	}
}
