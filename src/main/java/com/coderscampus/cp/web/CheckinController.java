
package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.service.ActivityLogService;
import com.coderscampus.cp.service.CheckinService;
import jakarta.servlet.http.HttpSession;
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
	public String home(ModelMap model,  HttpSession httpSession) {
		String uid = (String) httpSession.getAttribute("uid");
		List<Checkin> checkins = checkinService.findByUid(uid);
		model.put("checkins", checkins);
        model.addAttribute("pageTitle", "Checkin Read");
		model.put("isCheckin", true);
		System.out.println("checkins: " + checkins);
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
	public String create(Checkin checkin, @RequestParam("uid") String uid, @RequestParam("clientTimeZone") String clientTimeZone) {
		checkin = checkinService.saveByUid(checkin, uid, clientTimeZone);
		return "redirect:/checkin/";
	}

	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Checkin checkin = checkinService.findById(id);
//		if (checkin.getActivityLog().isEmpty()) {
//
//			List<ActivityLog> activityLog = new ArrayList<>();
//			checkin.setActivityLog(activityLog);
//		}
		model.put("checkin", checkin);
        model.addAttribute("pageTitle", "Checkin Update");
		model.put("isCheckin", true);
		return "checkin/update";
	}

	@PostMapping("/update")
	public String update(Checkin checkin, @RequestParam("uid") String uid, @RequestParam("clientTimeZone") String clientTimeZone) {
		checkinService.saveByUid(checkin, uid, clientTimeZone);
		return "redirect:/checkin/";
	}
	
	@PostMapping("/delete")
	public String delete(Checkin checkin) {
		checkinService.delete(checkin);
		return "redirect:/checkin/";
	}

	@ModelAttribute("roleList")
	public Checkin.Role[] getRoleList() {
		return Checkin.Role.values();
	}

	@ModelAttribute("codingType")
	public Checkin.CodingType[] getCodingType() {
		return Checkin.CodingType.values();
	}
}
