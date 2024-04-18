package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.repository.ActivityLogRepository;
import com.coderscampus.cp.repository.CheckinRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ActivityLogService {
    public void saveByUid(ActivityLog activityLog, String uid) {
//        setStudentAndUid(checkin, uid);
//        return checkinRepo.save(checkin);
    }
}
