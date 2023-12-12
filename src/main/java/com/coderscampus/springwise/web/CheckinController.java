package com.coderscampus.springwise.web;

import com.coderscampus.springwise.domain.Checkin;
import com.coderscampus.springwise.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/checkin")
public class CheckinController {
	
	@Autowired
	private CheckinService checkinService;
	
	@GetMapping("/")
	public String home(ModelMap model) {
		List<Checkin> checkins = checkinService.findAll();
		model.put("checkins", checkins);
		return "checkin/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		Checkin checkin = new Checkin();
		model.put("checkin", checkin);
		return "checkin/create";
	}

	@PostMapping("/create")
	public String create(Checkin checkin) {
		checkinService.save(checkin);
		return "redirect:/checkin/";
	}

	
	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Checkin checkin = checkinService.findById(id);
		model.put("checkin", checkin);
		return "checkin/update";
	}
	
	@PostMapping("/update")
	public String update(Checkin checkin) {
		checkinService.save(checkin);
		return "redirect:/checkin/";
	}
	
	@PostMapping("/delete")
	public String delete(Checkin checkin) {
		checkinService.delete(checkin);
		return "redirect:/checkin/";
	}
}
