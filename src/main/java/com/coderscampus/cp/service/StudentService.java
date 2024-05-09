package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.time.Instant;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public void save(Student student) {
        if (isValidNewStudent(student)) {
            studentRepo.save(student);
        }
        if (doesStudentExistInRepository(student)) {
            studentRepo.save(student);
        }
    }
    @Transactional
    public StudentDTO saveByUid(StudentDTO studentDTO, String uid) {
        Student foundStudent = studentRepo.findByUid(uid);
        if (foundStudent == null) {
        	Student student = new Student(studentDTO);
            student.setDateCreated(Instant.now());
            student.setUid(uid);
            foundStudent = studentRepo.save(student);
        }
        StudentDTO returnStudent = new StudentDTO(foundStudent);
        return returnStudent;
    }


    boolean doesStudentExistInRepository(Student student) {
        Optional<Student> existingStudent = studentRepo.findById(student.getId());

        if (existingStudent.isPresent() && existingStudent.get().getUid() != null
                && existingStudent.get().getUid().equals(student.getUid())) {
            return true;
        }
        return false;
    }

    boolean isValidNewStudent(Student student) {
        Student students = studentRepo.findByUid(student.getUid());
        if (students == null) {
            return true;
        }
        return false;
    }

    public StudentDTO findById(Long id) {
        return new StudentDTO(studentRepo.findById(id).get());
    }

    public boolean delete(Student student) {
        try {
            if (doesStudentExistInRepository(student)) {
                studentRepo.delete(student);
                Optional<Student> user = studentRepo.findById(student.getId());
                boolean foundUser = user.isPresent();
                if (foundUser) {
                    throw new RuntimeErrorException(null, "User was not deleted");
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    public StudentDTO findByUid(String uid) {
        Student student = studentRepo.findByUid(uid);
        return new StudentDTO(student);
    }
    
    @Transactional
	public StudentDTO updateStudent(StudentDTO studentDTO, String uid) {
		Student student = studentRepo.findByUid(uid);
		if (student != null) {
			student.setName(studentDTO.getName());
			student.setAssignmentNum(studentDTO.getAssignmentNum());
			student.setIde(studentDTO.getIde());
			student.setWillingToMentor(studentDTO.getWillingToMentor());
			student.setMentee(studentDTO.getMentee());
			studentRepo.save(student);
		} else {
			throw new IllegalArgumentException("No student found with UID: " + uid);
		}
		return new StudentDTO(student);
	}
}
