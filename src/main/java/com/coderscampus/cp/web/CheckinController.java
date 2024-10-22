package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.dto.CheckinDTO;
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
    public String home(ModelMap model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        List<CheckinDTO> checkins = checkinService.findByUid(uid);
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
    public String create(CheckinDTO checkinDTO, @RequestParam("uid") String uid) {
        checkinDTO = checkinService.saveByUid(checkinDTO, uid);
        return "redirect:/checkin/";
    }

    //start here - issue625
    @PostMapping("/activityLogCreate")
    public String createActivityLog(ModelMap model) {
//        ActivityLog activityLog = new ActivityLog();
//        model.put("activityLog", activityLog);
//        model.addAttribute("pageTitle", "ActivityLog Create");
//        model.put("isActivityLog", true);
//        return "activityLog/create";
    }


    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        CheckinDTO checkinDTO = checkinService.findById(id);
        model.put("checkin", checkinDTO);
        ActivityLog activityLog = new ActivityLog();
        model.put("activityLog", activityLog);
        model.addAttribute("pageTitle", "Checkin Update");
        model.put("isCheckin", true);
        return "checkin/update";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("checkin") Checkin checkin,
            @ModelAttribute("activityLog") ActivityLog activityLog) {
        checkin.getActivityLogs().add(activityLog);
        return "redirect:/checkin/";
    }

    @PostMapping("/update")
    public String update(CheckinDTO checkinDTO, @RequestParam("uid") String uid) {
        checkinService.saveByUid(checkinDTO, uid);
        return "redirect:/checkin/";
    }

    @PostMapping("/delete")
    public String delete(CheckinDTO checkinDTO, @RequestParam("uid") String uid) {
        checkinService.delete(checkinDTO, uid);
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
