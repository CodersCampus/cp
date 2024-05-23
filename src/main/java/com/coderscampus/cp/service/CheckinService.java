package com.coderscampus.cp.service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.coderscampus.cp.dto.CheckinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.info.InfoEndpointAutoConfiguration;
import org.springframework.stereotype.Service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepo;

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private InfoEndpointAutoConfiguration infoEndpointAutoConfiguration;

    public CheckinDTO saveByUid(CheckinDTO checkinDTO, String uid) {
        Checkin foundCheckin = checkinRepo.findById(checkinDTO.getId()).orElse(null);
        if (foundCheckin == null) {
            Checkin checkin = new Checkin(checkinDTO);
            setStudentAndUid(checkin, uid);
            foundCheckin = checkinRepo.save(checkin);

        } else {
            foundCheckin.setNextAssignment(checkinDTO.getNextAssignment());
            foundCheckin.setBlockers(checkinDTO.getBlockers());
            foundCheckin.setBlockerDescription(checkinDTO.getBlockerDescription());
            foundCheckin.setRole(checkinDTO.getRole());
            foundCheckin.setCodingType(checkinDTO.getCodingType());
            foundCheckin = checkinRepo.save(foundCheckin);
        }
        CheckinDTO returnCheckinDTO = new CheckinDTO(foundCheckin);

        return returnCheckinDTO;
    }


    private void setStudentAndUid(Checkin checkin, String uid) {
        Student student = studentRepo.findByUid(uid);
        if (student != null) {
            checkin.setStudent(student);
            checkin.setUid(uid);
        }
    }


    public List<Checkin> findAll() {
        return checkinRepo.findAll().stream().sorted(Comparator.comparing(Checkin::getDate).reversed()).collect(Collectors.toList());
    }

    public Checkin findById(Long id) {
        return checkinRepo.findById(id).get();
    }

    public void delete(Checkin checkin) {
        checkinRepo.delete(checkin);
    }

    public List<Checkin> findByUid(String uid) {
        return checkinRepo.findByUid(uid).stream().sorted(Comparator.comparing(Checkin::getDate).reversed()).collect(Collectors.toList());
    }
}

