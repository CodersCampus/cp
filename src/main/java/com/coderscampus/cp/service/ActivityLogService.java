package com.coderscampus.cp.service;

import java.util.*;

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
       if (activityLog == null || activityLog.getCheckin() == null || activityLog.getCheckin().getId() == null) {
           return null;
       }
        CheckinDTO checkinDTO = checkinService.findById(activityLog.getCheckin().getId());
        if (checkinDTO != null && checkinDTO.getId() != null) {
            return activityLogRepository.save(activityLog);
        }
        return null;
    }

    public ActivityLog findById(Long id) {
        if(id==null) {
            return null;
        }
        return activityLogRepository.findById(id).orElse(null);
    }
    
    public List<ActivityLog> findByCheckin(Long id) {
    	List<ActivityLog> activityLogList = new ArrayList<ActivityLog>();
    	if(id == null) {
    		return activityLogList;
    	}
    	Optional<Checkin> checkinOpt = checkinRepo.findById(id);
    	if(checkinOpt.isPresent()) {
    		return checkinOpt.get().getActivityLogs();
    	}
    	return activityLogList;
    }
    public List<ActivityLog> findByCheckinId(Long id) {
        List<ActivityLog> activityLogList = new ArrayList<ActivityLog>();
        if(id == null) {
            return activityLogList;
        }
        Optional<Checkin> checkinOpt = checkinRepo.findById(id);
        if(checkinOpt.isPresent()) {
            activityLogList = activityLogRepository.findByCheckinId(id);
            return activityLogList;
        }
        return activityLogList;

    }

    public Integer getNumberOfIssues() {return activityLogRepository.getNumberOfIssues();}

    public Map<String, Integer> getNumberForEachRole() {
        Map<String, Integer> activityLogMap = new HashMap<String, Integer>();
        String[] roles = new String[]{"OBSERVER", "CODER", "GUIDE", "SCRUM_MASTER", "PRODUCT_OWNER"};
        for (String role : roles) {
            Integer roleCount = getRoleStatsForActivity(role);
            activityLogMap.put(role, roleCount);
        }
        System.out.println(activityLogMap);
        return activityLogMap;
    }
        public Integer getRoleStatsForActivity (String role){
            Integer count = 0;
            List<ActivityLog> allActivityLogs = activityLogRepository.findAll();
            for (ActivityLog activityLog : allActivityLogs) {
                if (activityLog.getRole() != null && activityLog.getRole().name().equals(role)) {
                 count++;
                }
            }
            return count;
        }

        public Map<String, Integer> getNumberForEachType() {
        Map<String, Integer> activityLogMap = new HashMap<String, Integer>();
        String[] types = new String[]{"DESIGN", "CRUD", "CODE_REVIEW", "DOCUMENTATION"};
        for (String type : types) {
            Integer typeCount = getCodingTypeStatsForActivity(type);
            activityLogMap.put(type, typeCount);
        }
        System.out.println(activityLogMap);
        return activityLogMap;
    }
        public Integer getCodingTypeStatsForActivity (String type){
            Integer count = 0;
            List<ActivityLog> allActivityLogs = activityLogRepository.findAll();
            for (ActivityLog activityLog : allActivityLogs) {
                if (activityLog.getCodingType() != null && activityLog.getCodingType().name().equals(type)) {
                 count++;
                }
            }
            return count;
        }

}
