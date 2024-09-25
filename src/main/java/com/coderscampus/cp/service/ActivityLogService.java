package com.coderscampus.cp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.repository.ActivityLogRepository;
import com.coderscampus.cp.repository.CheckinRepository;

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
    
    public List<ActivityLog> findByCheckin(Long id) {
    	List<ActivityLog> activityLogList = new ArrayList<ActivityLog>();
    	if(id == null) {
    		return activityLogList;
    	}
    	Optional<Checkin> checkinOpt = checkinRepo.findById(id);
    	if(checkinOpt.isPresent()) {
    		return checkinOpt.get().getActivityLog(); 
    	}
    	return activityLogList;
    }


}
