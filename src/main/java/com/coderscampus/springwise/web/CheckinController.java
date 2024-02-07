package com.coderscampus.springwise.web;

import com.coderscampus.springwise.domain.Checkin;
import com.coderscampus.springwise.domain.Foobar;
import com.coderscampus.springwise.service.CheckinService;
import com.coderscampus.springwise.utility.EnumFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/checkin")
public class CheckinController {
	
	@Autowired
	private CheckinService checkinService;
	
	@GetMapping("/")
	public String home(ModelMap model) {
		List<Checkin> checkins = checkinService.findAll();
		model.put("checkins", checkins);
        model.addAttribute("pageTitle", "Checkin Read");
		model.put("isCheckin", true);
		return "checkin/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		Checkin checkin = new Checkin();
		model.put("checkin", checkin);
        model.addAttribute("pageTitle", "Checkin Create");
		model.put("isCheckin", true);
		return "checkin/create";
	}

	@PostMapping("/create")
	public String create(Checkin checkin, @RequestParam("uid") String uid) {
		checkin = checkinService.saveByUid(checkin, uid);
		return "redirect:/checkin/";
	}

	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Checkin checkin = checkinService.findById(id);
		model.put("checkin", checkin);
        model.addAttribute("pageTitle", "Checkin Update");
		model.put("isCheckin", true);
		return "checkin/update";
	}
	
	@PostMapping("/update")
	public String update(Checkin checkin, @RequestParam("uid") String uid) {
		checkinService.saveByUid(checkin, uid);
		return "redirect:/checkin/";
	}
	
	@PostMapping("/delete")
	public String delete(Checkin checkin) {
		checkinService.delete(checkin);
		return "redirect:/checkin/";
	}

//	@ModelAttribute("roleList")
//	public Checkin.Role[] getRoleList() {
//		return Checkin.Role.values();
//	}


	@ModelAttribute("roleList")
	public List<String> getRoleList() {
		return Arrays.stream(Checkin.Role.values())
				.map(EnumFormatUtil::formatEnumName) // Static import of the utility method
				.collect(Collectors.toList());
	}

//	@ModelAttribute("codingType")
//	public Checkin.CodingType[] getCodingType() {
//		return Checkin.CodingType.values();
//	}

	@ModelAttribute("codingType")
	public List<String> getCodingType() {
		return Arrays.stream(Checkin.CodingType.values())
				.map(EnumFormatUtil::formatEnumName) // Static import of the utility method
				.collect(Collectors.toList());
	}

}
