package com.coderscampus.cp.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.coderscampus.cp.domain.ActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.repository.CheckinRepository;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GoogleUIDValidationService googleUIDValidationService;

    public CheckinDTO saveByUid(CheckinDTO checkinDTO, String uid) {
        Checkin foundCheckin = new Checkin();
        Student student = studentService.findStudentByUid(uid);
        if (student == null || uid == null || checkinDTO == null || !googleUIDValidationService.isValidGoogleUID(uid)) {
            return null;
        }

        if (checkinDTO.getId() != null) {
            foundCheckin = checkinRepo.findById(checkinDTO.getId()).orElse(null);
            if (!foundCheckin.getUid().equals(uid)) {
                return null;
            }

        } else if (checkinDTO.getStudentId() != null && checkinDTO.getStudentId() != student.getId()) {

            return null;
        }

        foundCheckin.setNextAssignment(checkinDTO.getNextAssignment());
        foundCheckin.setBlocker(checkinDTO.getBlocker());
        foundCheckin.setSetup(checkinDTO.getSetUp());
        foundCheckin.setAvailable(checkinDTO.getAvailable());
        foundCheckin.setBlockerDescription(checkinDTO.getBlockerDescription());
        foundCheckin.setStudent(student);
        foundCheckin.setUid(student.getUid());
        foundCheckin = checkinRepo.save(foundCheckin);
        return new CheckinDTO(foundCheckin);
    }

    public List<CheckinDTO> findAll() {
        return checkinRepo.findAll().stream().sorted(Comparator.comparing(Checkin::getDate).reversed())
                .map(CheckinDTO::new).collect(Collectors.toList());
    }


    public CheckinDTO findById(Long id) {
        Checkin foundCheckin = checkinRepo.findById(id).orElse(null);
        if (foundCheckin != null) {
            CheckinDTO checkinDTO = new CheckinDTO(foundCheckin);
            return checkinDTO;
        }
        return null;
    }

    public Long delete(CheckinDTO checkinDTO, String uid) {
        if (uid == null || checkinDTO == null || checkinDTO.getId() == null) {
            return 0L;
        }
        Long id = checkinDTO.getId();
        Checkin foundCheckin = checkinRepo.findById(checkinDTO.getId()).orElse(null);
        if (foundCheckin != null && foundCheckin.getUid().equals(uid)) {
            checkinRepo.delete(foundCheckin);
        } else {
            id = 0L;
        }
        return id;
    }

    public List<CheckinDTO> findByUid(String uid) {
        List<Checkin> checkins = checkinRepo.findByUid(uid).stream()
                .sorted(Comparator.comparing(Checkin::getDate).reversed()).collect(Collectors.toList());
        List<CheckinDTO> checkinDTOS = new ArrayList<>();
        for (Checkin checkin : checkins) {
            CheckinDTO checkinDTO = new CheckinDTO(checkin);
            checkinDTOS.add(checkinDTO);
        }
        return checkinDTOS;
    }

    public List<ActivityLog> findActivityLogsByCheckinId(Long id) {
        Checkin foundCheckin = checkinRepo.findById(id).orElse(null);
        if (foundCheckin != null) {
            return foundCheckin.getActivityLogs();
        } else {
            return new ArrayList<>();
        }
    }

    public List<CheckinDTO> getSortedCheckinsByUid(String uid) {
        return checkinRepo.findByUid(uid).stream()
                .filter(checkin -> Boolean.TRUE.equals(checkin.getBlocker()))
                .sorted(Comparator.comparing(Checkin::getDate).reversed())
                .map(CheckinDTO::new)
                .collect(Collectors.toList());
    }

    public List<CheckinDTO> getCodersActivities(String uid) {
        return checkinRepo.findCodersActivities();
    }
}