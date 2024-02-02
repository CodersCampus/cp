package com.coderscampus.springwise.service;

import com.coderscampus.springwise.domain.Checkin;
import com.coderscampus.springwise.domain.Student;
import com.coderscampus.springwise.repository.CheckinRepository;
import com.coderscampus.springwise.repository.StudentRepository;
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
		List<Student> students = studentRepo.findByUid(uid);
		if (students.size() > 1)
			throw new IllegalStateException("Shouldn't have more than one student per uid");
		if (!students.isEmpty()) {
			Student student = students.get(0);
			checkin.setStudent(student);
			checkin.setUid(uid);
		}
		return checkinRepo.save(checkin);
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