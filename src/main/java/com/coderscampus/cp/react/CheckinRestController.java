package com.coderscampus.cp.react;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.service.CheckinService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@RestController
@RequestMapping("/api/check-in")

public class CheckinRestController {
    private final CheckinService checkinService;

    public CheckinRestController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }
    @PostMapping("/start-check-in")
    public ResponseEntity<Checkin> createAndStartStudentCheckin(
            @RequestParam Long studentId) {
        Checkin newCheckin = checkinService.createStudentCheckin(studentId);
        return ResponseEntity.ok(newCheckin);
    }
    @PostMapping("/finish-check-in")
    public ResponseEntity<Checkin> finishStudentCheckin(
            @RequestParam Long studentId) {
        Checkin finishedCheckin = checkinService.endStudentCheckin(studentId);
        System.out.println("Controller: Finished Checkin with ID: " + finishedCheckin.getId());
        return ResponseEntity.ok(finishedCheckin);
    }
}