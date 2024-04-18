package com.coderscampus.cp.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.repository.ActivityLogRepository;

@Service
public class ActivityLogService {

	@Autowired
	private ActivityLogRepository activityLogRepo;

	public ActivityLog saveByUid(ActivityLog activityLog, String uid) {
		if(uid.isEmpty()) {
			return null;
		}
		ActivityLog foundActivityLog = activityLogRepo.findByUid(uid);
		if (foundActivityLog == null) {
			activityLog.setDate(Instant.now());
			activityLog.setUid(uid);
			return activityLogRepo.save(activityLog);
		}
		return activityLogRepo.save(foundActivityLog);

	}
}
