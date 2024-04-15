package com.coderscampus.cp.service;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepo;

    @Autowired
    private StudentRepository studentRepo;

    public Checkin saveByUid(Checkin checkin, String uid) {
//        if (checkinRepo.findByUid(uid) == null) {
//            checkin.setDate(Instant.now());
//        }
        setDateIfNull(checkin);
        setStudentAndUid(checkin, uid);
        return checkinRepo.save(checkin);
    }

    private void setStudentAndUid(Checkin checkin, String uid) {
        Student student = studentRepo.findByUid(uid);
        if (student != null) {
            checkin.setStudent(student);
            checkin.setUid(uid);
        }
    }

    private void setDateIfNull(Checkin checkin) {
        if (checkin.getDate() == null) {
            checkin.setDate(Instant.now());
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

