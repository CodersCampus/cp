package com.coderscampus.cp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.service.ActivityLogService;

@Controller
@RequestMapping("/activityLog")
public class ActivityLogController {
    @Autowired
    private ActivityLogService activityLogService;

    @PostMapping("/create")
    public String postCreate(ActivityLog activityLog, @RequestParam("uid") String uid) {
        activityLogService.saveByUid(activityLog, uid);
        return "redirect:/checkin/update";
    }
}
