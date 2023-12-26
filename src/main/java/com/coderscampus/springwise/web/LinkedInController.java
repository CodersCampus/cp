package com.coderscampus.springwise.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.springwise.domain.LinkedIn;
import com.coderscampus.springwise.service.LinkedInService;

@Controller
@RequestMapping("/linkedIn/linkedIn")
public class LinkedInController {
	
	@Autowired
	private LinkedInService linkedInService;
	
	@GetMapping("/")
	public String home(ModelMap model) {
		List<LinkedIn> linkedIns = linkedInService.findAll();
		model.put("linkedIn", linkedIns);
		return "linkedIn/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		LinkedIn linkedIn = new LinkedIn();

		model.put("linkedIn", linkedIn);
		return "linkedIn/create";
	}

	@PostMapping("/create")
	public String create(LinkedIn linkedIn) {
		System.out.println(linkedIn);
		linkedInService.save(linkedIn);

		return "redirect:/linkedIn/";
	}

	
	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		LinkedIn linkedIn = linkedInService.findById(id);
		model.put("linkedIn", linkedIn);
		
		return "linkedIn/update";
	}
	
	@PostMapping("/update")
	public String update(LinkedIn linkedIn) {
		System.out.println(linkedIn.toString());

		linkedInService.save(linkedIn);
		return "redirect:/linkedIn/";
	}
	
	@PostMapping("/delete")
	public String delete(LinkedIn linkedIn) {
		linkedInService.delete(linkedIn);
	
		return "redirect:/linkedIn/";
		
	}
}
