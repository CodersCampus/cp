package com.coderscampus.practice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.practice.service.SeedService;

@Controller
@RequestMapping("/practice")
public class PracticeController {
	@Autowired
	private SeedService seedService;

	@GetMapping("")
	public String home(ModelMap model) {
		seedService.populateData();

		return "/practice/index";
	}

}
