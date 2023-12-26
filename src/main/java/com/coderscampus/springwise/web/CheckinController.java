package com.coderscampus.springwise.web;

import com.coderscampus.springwise.domain.Checkin;
import com.coderscampus.springwise.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
		//	Dave N. added to remove Checkin Nav link from displaying on this page
		model.put("isCheckin", true);
		return "checkin/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		Checkin checkin = new Checkin();
		model.put("checkin", checkin);
		//	Dave N. added to remove Checkin Nav link from displaying on this page
		model.put("isCheckin", true);
		return "checkin/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute Checkin checkin) {
		System.out.println("in/create");
		checkinService.save(checkin);
		return "redirect:/checkin/";
	}

	
	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Checkin checkin = checkinService.findById(id);
		model.put("checkin", checkin);
		//	Dave N. added to remove Checkin Nav link from displaying on this page
		model.put("isCheckin", true);
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
