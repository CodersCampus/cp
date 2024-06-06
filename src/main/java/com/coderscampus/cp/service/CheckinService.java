package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepo;

    @Autowired
    private StudentRepository studentRepo;

    public CheckinDTO saveByUid(CheckinDTO checkinDTO, String uid) {
        Checkin foundCheckin = null;
        if (checkinDTO.getId() != null) {
            foundCheckin = checkinRepo.findById(checkinDTO.getId()).orElse(null);
        }

        if (foundCheckin == null) {
            Checkin checkin = new Checkin(checkinDTO, uid);
            setStudentFromUid(checkin, uid);
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

    private void setStudentFromUid(Checkin checkin, String uid) {
        Student student = studentRepo.findByUid(uid);
        if (student != null) {
            checkin.setStudent(student);
        }
    }

    public List<Checkin> findAll() {
        return checkinRepo.findAll().stream().sorted(Comparator.comparing(Checkin::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Checkin findById(Long id) {
        return checkinRepo.findById(id).get();
    }

    public void delete(CheckinDTO checkinDTO, String uid) {
        Checkin foundCheckin = checkinRepo.findById(checkinDTO.getId()).orElse(null);
        if (foundCheckin != null && foundCheckin.getUid().equals(uid)) {
            checkinRepo.delete(foundCheckin);
        }
    }

    public List<CheckinDTO> findByUid(String uid) {
        List<Checkin> checkins = checkinRepo.findByUid(uid).stream()
                .sorted(Comparator.comparing(Checkin::getDate).reversed()).collect(Collectors.toList());
        return checkins.stream().map(CheckinDTO::new).collect(Collectors.toList());

    }
}
