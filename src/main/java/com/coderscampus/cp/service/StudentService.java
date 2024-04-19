package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.StudentRepository;
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

    public Student saveByUid(Student student, String uid) {
        Student foundStudent = studentRepo.findByUid(uid);
        if (foundStudent == null) {
            student.setDateCreated(Instant.now());
            student.setUid(uid);
            return studentRepo.save(student);
        }
        return foundStudent;

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
            return false;
        }
        return true;
    }

    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    public Student findById(Long id) {
        return studentRepo.findById(id).get();
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

    public Student findByUid(String uid) {
        return studentRepo.findByUid(uid);
    }
}
