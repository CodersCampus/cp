package com.coderscampus.springwise.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CanvasBackController {
	
	@GetMapping ("/canvas-back")
	public String getCanvasBackDuck (ModelMap model) {
		
		return "canvas-back";
	}

}
