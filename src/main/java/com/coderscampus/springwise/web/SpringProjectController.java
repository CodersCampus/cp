package com.coderscampus.springwise.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringProjectController {

//	@Autowired
//	private SpringWiseService springService;
	
	@GetMapping("/")
	public String getIndex(ModelMap model) {
		List<Integer> nums = new ArrayList<>();
		
		for (int i=0; i<69; i++) {
			nums.add(i);
		}
		model.put("nums", nums);
		
		return "index";
	}
	
}
