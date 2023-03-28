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
	public String getHomePage() {
		return "dogs";
	}
	
	@GetMapping("/create")
	public String getHome (ModelMap model) {
		
		Dog dog = new Dog();
		
		model.put("dog", dog);
		
		return "createdog";
	}

	@PostMapping("/save-dog")
	public String saveDog(Dog dog) {
		dogService.saveDog(dog);
		return "redirect:/dog/all-dogs";
	}
	
	@GetMapping("/all-dogs")
	public String getDogPage (ModelMap model) {
		List<Dog> dogs = dogService.findAll();
		
		model.put("dogs", dogs);
		return "dogs";
	}
	
	@GetMapping("/{id}")
	public String fetchDog(ModelMap model, @PathVariable Long id) {
		Dog dog = dogService.findById(id);
		model.put("dog", dog);
		System.out.println(id);
		System.out.println(dog.getBreed());
		return "dog";
	}
}
