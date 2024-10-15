package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.ActivityLog;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.service.ActivityLogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/activityLog")
public class ActivityLogController {
    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        // START HERE AND NEED A CHECKIN TO GET ANYTHING DONE WITH ACTIVITYLOG
        ActivityLog activityLog = new ActivityLog();
        model.put("activityLog", activityLog);
        model.addAttribute("pageTitle", "activityLog Create");
        model.put("comment", "");
//        ActivityLog activityLog = new ActivityLog();
//        model.put("activityLog", activityLog);
//        model.addAttribute("pageTitle", "ActivityLog Create");
//        model.put("isActivityLog", true);
        return "activityLog/create";
    }
    @PostMapping("/create")
    public String postCreate(@ModelAttribute("activityLog") ActivityLog activityLog) {
        activityLogService.save(activityLog);
        return "redirect:/activityLog/read";
    }
    @GetMapping("/read")
    public String home(ModelMap model, HttpSession httpSession) {
//        List<ActivityLog> activityLogs = activityLogService.findByUid(uid);
//        model.put("activityLogs", activityLogs);
//        model.addAttribute("pageTitle", "ActivityLog Read");
//        model.put("isActivityLog", true);
        return "activityLog/read";
    }



//    @PostMapping("/create")
//    public String create(ActivityLogDTO activityLogDTO, @RequestParam("uid") String uid) {
////        activityLogDTO = activityLogService.saveByUid(activityLogDTO, uid);
//        return "redirect:/activityLog/";
//    }


    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
//        ActivityLogDTO activityLogDTO = activityLogService.findById(id);
//        model.put("activityLog", activityLogDTO);
//        ActivityLog activityLog = new ActivityLog();
//        model.put("activityLog", activityLog);
//        model.addAttribute("pageTitle", "ActivityLog Update");
//        model.put("isActivityLog", true);
        return "activityLog/update";
    }

    @PostMapping("/update/{id}")
    public String updateWithId(@ModelAttribute("activityLog") ActivityLog activityLog) {
//        activityLog.getActivityLog().add(activityLog);
        return "redirect:/activityLog/";
    }

    @PostMapping("/update")
    public String update(ActivityLog activityLog) {
//        activityLogService.saveByUid(activityLog, uid);
        return "redirect:/activityLog/";
    }
    @ModelAttribute("roleList")
    public Checkin.Role[] getRoleList() {
        return Checkin.Role.values();
    }

    @ModelAttribute("codingType")
    public Checkin.CodingType[] getCodingType() {
        return Checkin  .CodingType.values();
    }
}
