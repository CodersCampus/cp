package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.repository.ActivityLogRepository;
import com.coderscampus.cp.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityLogService {
    @Autowired
    private CheckinRepository checkinRepo;
    @Autowired
    private StudentService studentService;
    @Autowired
    private GoogleUIDValidationService googleUIDValidationService;
    @Autowired
    private CheckinService checkinService;
    @Autowired
    private ActivityLogRepository activityLogRepository;

    public ActivityLog save(ActivityLog activityLog) {
        CheckinDTO checkinDTO = checkinService.findById(activityLog.getCheckin().getId());
        if (checkinDTO != null && checkinDTO.getId() != null) {
            return activityLogRepository.save(activityLog);
        }
        return null;
    }

    public ActivityLog findById(Long id) {
        ActivityLog foundActivityLog = activityLogRepository.findById(id).orElse(null);
        return foundActivityLog;
    }


}
