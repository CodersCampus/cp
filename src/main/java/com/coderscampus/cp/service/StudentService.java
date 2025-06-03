package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;
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
            Student student = new Student(studentDTO, uid);
            student.setUid(uid);
            foundStudent = studentRepo.save(student);

        } else {
            foundStudent.setName(studentDTO.getName());
            foundStudent.setAssignmentNum(studentDTO.getAssignmentNum());
            foundStudent.setIde(studentDTO.getIde());
            foundStudent.setWillingToMentor(studentDTO.getWillingToMentor());
            foundStudent.setMentee(studentDTO.getMentee());
            foundStudent = studentRepo.save(foundStudent);

        }
        StudentDTO returnStudent = new StudentDTO(foundStudent);
        return returnStudent;
    }

    @Transactional
    public Student findStudentByUid(String uid) {
        Student foundStudent = studentRepo.findByUid(uid);
        if (foundStudent == null) {
            return null;
        }
        return foundStudent;
    }

    boolean doesStudentExistInRepository(Student student) {
        Optional<Student> existingStudent = studentRepo.findById(student.getId());

        return existingStudent.isPresent() && existingStudent.get().getUid() != null
                && existingStudent.get().getUid().equals(student.getUid());
    }

    boolean isValidNewStudent(Student student) {
        Student students = studentRepo.findByUid(student.getUid());
        return students == null;
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
        if (student == null) {
            return null;
        }
        return new StudentDTO(student);
    }


    public List<StudentDTO> findAllAsDTOs() {
        List<Student> allStudents = studentRepo.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student student : allStudents) {
            StudentDTO studentDTO = new StudentDTO(student);
            studentDTOS.add(studentDTO);
        }

        return studentDTOS;
    }

}
