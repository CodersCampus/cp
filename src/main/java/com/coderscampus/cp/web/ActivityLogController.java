package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Checkin;
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
    @PostMapping("/create")
    public String update(Checkin checkin, @RequestParam("uid") String uid, @RequestParam("clientTimeZone") String clientTimeZone) {
        checkinService.saveByUid(checkin, uid, clientTimeZone);
        return "redirect:/checkin/";
    }
}
