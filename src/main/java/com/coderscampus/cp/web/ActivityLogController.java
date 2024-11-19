package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/activityLog")
public class ActivityLogController {
    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    private CheckinRepository checkinRepository;

    @PostMapping("/create")
    public String postCreate(ActivityLog activityLog, @RequestParam("id") Long id) {
        activityLog.setId(null);
        Checkin checkin = checkinRepository.findById(id).get();
        activityLog.setCheckin(checkin);
        activityLogService.save(activityLog);
        return "redirect:/checkin/update/" + id;
    }
}
