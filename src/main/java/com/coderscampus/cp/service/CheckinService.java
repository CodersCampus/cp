package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckinService {

    private CheckinRepository checkinRepo;
    private StudentRepository studentRepo;

    public CheckinService(CheckinRepository checkinRepo, StudentRepository studentRepo) {
        this.checkinRepo = checkinRepo;
        this.studentRepo = studentRepo;
    }


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

    @Transactional
    public Checkin createStudentCheckin(Long studentId) {
        Instant now = Instant.now();
//      Prevents accidental opening of multiple check-ins
        if(checkinRepo.findByStudentIdAndCheckoutTimeIsNull(studentId).isPresent()){
            System.out.println("Unclosed Checkin Found for Student with ID: " + studentId);
            endStudentCheckin(studentId);
        }
        System.out.println("Creating Checkin for Student with ID: " + studentId);
        Checkin newCheckin = new Checkin();
        Student existingStudent = studentRepo.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        newCheckin.setStudent(existingStudent);
        newCheckin.setStartTime(now);
        checkinRepo.save(newCheckin);
        existingStudent.getCheckin().add(newCheckin);
        studentRepo.save(existingStudent);
        return newCheckin;
    }
    @Transactional
    public Checkin endStudentCheckin(Long studentId) {
        Instant now = Instant.now();
        System.out.println("Creating Checkout for Student with ID: " + studentId);
        Student existingStudent = studentRepo.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Student not found with id: " + studentId));
        Checkin unclosedCheckin = checkinRepo.findByStudentIdAndCheckoutTimeIsNull(studentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Unclosed Check-in not found for student with id: " + studentId));
        unclosedCheckin.setEndTime(now);
        checkinRepo.save(unclosedCheckin);
        existingStudent.getCheckin().add(unclosedCheckin);
        System.out.println("Service: Saving finished checkin with ID: " + unclosedCheckin.getId());
        return unclosedCheckin;
    }

}