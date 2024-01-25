package com.coderscampus.springwise.service;

import com.coderscampus.springwise.domain.Student;
import com.coderscampus.springwise.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
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
        student.setUid(uid);
        return save(student);
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
        List<Student> students = studentRepo.findByUid(student.getUid());
        if (students.size() > 0) {

            return false;
        }
        return student.getId() == 0;
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

}
