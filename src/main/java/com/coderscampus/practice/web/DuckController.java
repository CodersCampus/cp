package com.coderscampus.practice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DuckController {
	
	@GetMapping("/ducks")
	public String getDucks() {
		
		return "ducks";
	}

}
