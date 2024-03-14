package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepo;

    @Autowired
    private StudentRepository studentRepo;

    public Checkin save(Checkin checkin) {
        if (checkin.getDate() == null) {
            checkin.setDate(LocalDateTime.now());
        }
        return checkinRepo.save(checkin);
    }

	public Checkin saveByUid(Checkin checkin, String uid) {
		Student students = studentRepo.findByUid(uid);
        if(students != null){
            checkin.setStudent(students);
            checkin.setUid(uid);
        }
		return save(checkin);
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

}