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
	private final ActivityLogRepository activityLogRepository;
	private final CheckinRepository checkinRepository;
	public ActivityLogService(CheckinRepository checkinRepository, ActivityLogRepository activityLogRepository) {
		super();
		this.checkinRepository = checkinRepository;
		this.activityLogRepository = activityLogRepository;
	}
	
	@Transactional
    public void saveByUid(ActivityLog activityLog, String uid) {
		//Please examine objects here in DeBug
		Checkin checkin = activityLog.getCheckin();
		List<ActivityLog> activityLogList = checkin.getActivityLog();
		activityLogList.add(activityLog);
		checkinRepository.save(checkin);
		activityLogRepository.save(activityLog);
		
//        setStudentAndUid(checkin, uid);
//        setDateIfNull(checkin, clientTimeZone);
//        return checkinRepo.save(checkin);
    }
}
