package com.coderscampus.practice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MallardController {
	
	@GetMapping("/mallard")
	
	public String getMallard() {
		
		return "mallard";
	}
	
}
