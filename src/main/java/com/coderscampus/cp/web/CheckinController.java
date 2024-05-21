package com.coderscampus.cp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.service.CheckinService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    @GetMapping("/")
    public String home(ModelMap model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        List<Checkin> checkins = checkinService.findByUid(uid);
        model.put("checkins", checkins);
        model.addAttribute("pageTitle", "Checkin Read");
        model.put("isCheckin", true);
        return "checkin/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
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
        ActivityLog activityLog = new ActivityLog();
        model.put("activityLog", activityLog);
        model.addAttribute("pageTitle", "Checkin Update");
        model.put("isCheckin", true);
        return "checkin/update";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("checkin") Checkin checkin, @ModelAttribute("activityLog") ActivityLog activityLog) {
        checkin.getActivityLog().add(activityLog);
        return "redirect:/checkin/";
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

    @ModelAttribute("roleList")
    public Checkin.Role[] getRoleList() {
        return Checkin.Role.values();
    }

    @ModelAttribute("codingType")
    public Checkin.CodingType[] getCodingType() {
        return Checkin.CodingType.values();
    }
}
