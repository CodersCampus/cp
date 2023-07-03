package com.coderscampus.springwise.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthTestingController {

	@GetMapping("/")
	public String home(ModelMap model) {
		return "auth/test";
	}
}
