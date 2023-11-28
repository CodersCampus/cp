package com.coderscampus.practice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.practice.domain.Airplane;
import com.coderscampus.practice.service.AirplaneService;

@Controller
@RequestMapping("/airplane")
public class AirplaneController {
	
	@Autowired
	private AirplaneService airplaneService;

	
	@GetMapping("/")
	public String home(ModelMap model) {
		List<Airplane> airplanes = airplaneService.findAll();
		model.put("airplanes", airplanes);
		return "airplane/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		Airplane airplane = new Airplane();
		model.put("airplane", airplane);
		return "airplane/create";
	}

	@PostMapping("/create")
	public String create(Airplane airplane) {
		airplaneService.save(airplane);
		return "redirect:/airplane/";
	}

	
	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Airplane airplane = airplaneService.findById(id);
		model.put("airplane", airplane);
		return "airplane/update";
	}
	
	@PostMapping("/update")
	public String update(Airplane airplane) {
		airplaneService.save(airplane);
		return "redirect:/airplane/";
	}
	
	@PostMapping("/delete")
	public String delete(Airplane airplane) {
		airplaneService.delete(airplane);
		return "redirect:/airplane/";
	}
}
