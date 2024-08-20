package com.coderscampus.cp.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GoogleUIDValidationService googleUIDValidationService;

    public CheckinDTO saveByUid(CheckinDTO checkinDTO, String uid) {
        if (uid == null) {
            return null;
        }

        if (checkinDTO == null) {
            return null;
        }

        if (!googleUIDValidationService.isValidGoogleUID(uid)) {
            return null;
        }

        Checkin foundCheckin = null;
        if (checkinDTO.getId() != null) {
            foundCheckin = checkinRepo.findById(checkinDTO.getId()).orElse(null);
        }

        if (foundCheckin == null) {
            foundCheckin = createCheckin(checkinDTO, uid);

        } else {
            if (!foundCheckin.getUid().equals(uid)) {
                return null;
            }
            foundCheckin.setNextAssignment(checkinDTO.getNextAssignment());
            foundCheckin.setBlockers(checkinDTO.getBlockers());
            foundCheckin.setBlockerDescription(checkinDTO.getBlockerDescription());
            foundCheckin.setRole(checkinDTO.getRole());
            foundCheckin.setCodingType(checkinDTO.getCodingType());
            foundCheckin = checkinRepo.save(foundCheckin);
        }

        CheckinDTO returnCheckinDTO = foundCheckin != null ? new CheckinDTO(foundCheckin) : null;


        return returnCheckinDTO;
    }

    private Checkin createCheckin(CheckinDTO checkinDTO, String uid) {
        Checkin checkin = new Checkin();
        Student student = studentService.findStudentByUid(uid);
        System.out.println("STUDENT IS HERE, LOOK \n \t" + student + " \n" + checkinDTO);
        if (checkinDTO.getStudentId() != null && student.getId() == checkinDTO.getStudentId()) {
            setStudentFromUid(checkin, uid);
            return checkinRepo.save(checkin);
        } else {
            return null;
        }
    }

    private void setStudentFromUid(Checkin checkin, String uid) {
        Student student = studentRepo.findByUid(uid);
        if (student != null) {
            checkin.setStudent(student);
        }
    }

    public List<CheckinDTO> findAll() {
        System.out.println("WE GOT TO FINDALL");
        //        return checkinRepo.findAll().stream().sorted(Comparator.comparing(Checkin::getDate).reversed()).map(CheckinDTO::new).collect(Collectors.toList());
    return null;
    }

    public CheckinDTO findById(Long id, String uid) {
        Checkin foundCheckin = checkinRepo.findById(id).orElse(null);
        if (foundCheckin != null && foundCheckin.getUid().equals(uid)) {
            CheckinDTO returnCheckinDTO = new CheckinDTO(foundCheckin);
            return returnCheckinDTO;
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
        List<Checkin> checkins = checkinRepo.findByUid(uid).stream().sorted(Comparator.comparing(Checkin::getDate).reversed()).collect(Collectors.toList());
        System.out.println("WE GOT HERE" + checkins.size());
//        return checkins.stream().map(CheckinDTO::new).collect(Collectors.toList());
        List<CheckinDTO> checkinDTOS = new ArrayList<>();
        for(Checkin checkin : checkins) {
            CheckinDTO checkinDTO = new CheckinDTO(checkin);
            checkinDTOS.add(checkinDTO);
        }
        return checkinDTOS;
    }
}