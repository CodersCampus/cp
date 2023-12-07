package com.coderscampus.practice.web;

import com.coderscampus.practice.domain.Frog;
import com.coderscampus.practice.service.FrogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/frog")
public class FrogController {
	
	@Autowired
	private FrogService frogService;
	
	@GetMapping("/")
	public String home(ModelMap model) {
		List<Frog> frogs = frogService.findAll();
		model.put("frogs", frogs);
		return "frog/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		Frog frog = new Frog();
		model.put("frog", frog);
		return "frog/create";
	}

	@PostMapping("/create")
	public String create(Frog frog) {
		frogService.save(frog);
		return "redirect:/frog/";
	}

	
	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Frog frog = frogService.findById(id);
		model.put("frog", frog);
		return "frog/update";
	}
	
	@PostMapping("/update")
	public String update(Frog frog) {
		frogService.save(frog);
		return "redirect:/frog/";
	}
	
	@PostMapping("/delete")
	public String delete(Frog frog) {
		frogService.delete(frog);
		return "redirect:/frog/";
	}
}
