package com.coderscampus.cp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.StudentRepository;

import jakarta.transaction.Transactional;


@SpringBootTest
class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepo;


    @Test
    void testIsValidStudentUpdate() {
        String uid = UUID.randomUUID().toString();
        Student existingStudent = new Student(0, "bobby", 17, uid);
        existingStudent = studentRepo.save(existingStudent);
        Student student = new Student(existingStudent.getId(), "bobby", 12, uid);
        assertTrue(studentService.doesStudentExistInRepository(student));
        studentRepo.delete(existingStudent);
    }


    @Test
    void testIsValidNewStudent() {
        String uid = UUID.randomUUID().toString();
        Student student = new Student(0, "bobby", 12, uid);
        assertTrue(studentService.isValidNewStudent(student));
        studentRepo.delete(student);

    }


    @Test
    void testValidNewStudentIfExists() {
        String uid = UUID.randomUUID().toString();
        Student student = new Student(0, "bobby", 12, uid);
        Student existingStudent = new Student(0, "bobby", 17, uid);
        studentRepo.save(existingStudent);
        assertFalse(studentService.isValidNewStudent(student));
        studentRepo.delete(existingStudent);
        studentRepo.delete(student);
    }


    @Test
    void testCanAddSecondStudent() {
        String uid = UUID.randomUUID().toString();
        String uid2 = UUID.randomUUID().toString();
        Student student2 = new Student(1, "bobby", 12, uid2);
        Student student = new Student(0, "bobby", 12, uid);
        studentRepo.save(student);
        studentRepo.save(student2);
        assertFalse(studentService.isValidNewStudent(student));
        assertFalse(studentService.isValidNewStudent(student2));
        studentRepo.delete(student2);
        studentRepo.delete(student);
    }

    @Test
    void testStudentSaveByUid() {
        String uid = UUID.randomUUID().toString();
        Student student = new Student(554, "bobby", 12, uid);
        StudentDTO studentDTO = new StudentDTO(student);
        StudentDTO result = studentService.saveByUid(studentDTO, uid);
        assertEquals("bobby", result.getName());
        Student studentResult = studentRepo.findByUid(uid);
        assertEquals("bobby", studentResult.getName());
    }
    @Transactional
    @Test
    void testUpdateStudentByUid() {
    	String uid = UUID.randomUUID().toString();
        Student student = new Student(554, "bobby", 12, uid);
        StudentDTO studentDTO = new StudentDTO(student);
        studentDTO.setName("Kevin");
        studentDTO.setAssignmentNum(9);
        studentDTO.setIde("Eclipse");
        studentDTO.setMentee("Bobby");
        studentDTO.setWillingToMentor(true);
        studentService.saveByUid(studentDTO, uid);
        assertEquals("Kevin", student.getName());
        
        
    }

}
