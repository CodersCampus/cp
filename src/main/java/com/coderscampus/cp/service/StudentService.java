package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public Student save(Student student) {
        if (isValidNewStudent(student)) {
            return studentRepo.save(student);
        }
        if (isValidStudentUpdateOrDelete(student)) {
            return studentRepo.save(student);
        }
        return null;
    }
    @Transactional
    public StudentDTO saveByUid(Student student, String uid) {
        Student foundStudent = studentRepo.findByUid(uid);
        if (foundStudent == null) {
            student.setDateCreated(Instant.now());
            student.setUid(uid);
            foundStudent = studentRepo.save(student);
        }
        StudentDTO returnStudent = new StudentDTO(foundStudent);
       
		
        return returnStudent;

    }


    boolean isValidStudentUpdateOrDelete(Student student) {
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

    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    public StudentDTO findById(Long id) {
        return new StudentDTO(studentRepo.findById(id).get());
    }

    public boolean delete(Student student) {
        try {
            if (isValidStudentUpdateOrDelete(student)) {
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
        System.out.println("Student" + student);
        return new StudentDTO(studentRepo.findByUid(uid));
    }
}
