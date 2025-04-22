package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.service.ActivityLogService;
import com.coderscampus.cp.service.CheckinService;
import com.coderscampus.cp.service.UserStatusService;
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
    @Autowired
    private ActivityLogService activityLogService;
    @Autowired
    private UserStatusService userStatusService;

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
    public String getCreate(ModelMap model, HttpSession httpSession) {
        String userEmail = (String) httpSession.getAttribute("email");
        Integer nextAssignment = userStatusService.getUserNextAssignment(userEmail);
        Checkin checkin = new Checkin();
        checkin.setNextAssignment(nextAssignment);
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

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        CheckinDTO checkinDTO = checkinService.findById(id);
        List<ActivityLog> activityLogs = activityLogService.findByCheckinId(id);
        model.put("checkin", checkinDTO);
        model.put("activityLogs", activityLogs);
        ActivityLog activityLog = new ActivityLog();
        model.put("activityLog", activityLog);
        model.addAttribute("pageTitle", "Checkin Update");
        model.addAttribute("roleList", ActivityLog.Role.values());
        model.addAttribute("codingType", ActivityLog.CodingType.values());
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

    @GetMapping("/blockers")
    public String getCheckinsForBlockerReadButton(ModelMap model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        List<CheckinDTO> checkins = checkinService.getSortedCheckinsByUid(uid);
        model.put("checkins", checkins);
        model.addAttribute("pageTitle", "Blocker Read");
        model.put("isCheckin", true);
        return "checkin/blocker-read";
    }

    @GetMapping("/activities")
    public String getActivities(ModelMap model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        List<CheckinDTO> checkins = checkinService.getCodersActivities(uid);
        model.put("checkins", checkins);
        model.addAttribute("pageTitle", "Activities");
        model.put("isCheckin", true);
        return "checkin/activities-read";
    }

}

