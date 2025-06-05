package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepo;

    Student student1;
    Student student2;

    StudentDTO studentDTO1;
    StudentDTO studentDTO2;

    String student1Uid;
    String student2Uid;


    @BeforeEach
    void prepData() {

        studentDTO1 = new StudentDTO();
        studentDTO2 = new StudentDTO();

        student1Uid = UUID.randomUUID().toString();
        student2Uid = UUID.randomUUID().toString();

        student1 = new Student(student1Uid, "name1", 1, "IntelliJ", false, "mentor1", null);
        student2 = new Student(student2Uid, "name2", 2, "IntelliJ", false, "mentor2", null);

        student1 = studentRepo.save(student1);
        student2 = studentRepo.save(student2);

    }
    @AfterEach
    void cleanUpData() {
        studentRepo.delete(student1);
        studentRepo.delete(student2);
    }


    @Test
    void testIsValidStudentUpdate() {

        StudentDTO studentDTO = new StudentDTO(student1);
        studentDTO.setName("Lucas");
        studentDTO.setAssignmentNum(12);
        studentDTO.setIde("Eclipse");
        studentDTO.setWillingToMentor(true);
        studentDTO.setMentee("Fred");
        Student student = new Student(studentDTO, student1Uid);
        student.setUid(student1Uid);

        assertTrue(studentService.doesStudentExistInRepository(student));
        assertEquals(student1.getId(), student.getId());
        assertEquals("Lucas", student.getName());
        assertEquals(12, student.getAssignmentNum());
        assertEquals("Eclipse", student.getIde());
        assertEquals(true, student.getWillingToMentor());
        assertEquals("Fred", student.getMentee());

    }

    @Test
    void testIsValidNewStudent() {
        String uid = UUID.randomUUID().toString();
        Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
        assertTrue(studentService.isValidNewStudent(student));
        studentRepo.delete(student);
    }

    @Test
    void testValidNewStudentIfExists() {
        String uid = UUID.randomUUID().toString();
        Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
        Student existingStudent = new Student(uid, "Bobby", 17, "IntelliJ", false, "name", null);
        studentRepo.save(existingStudent);
        assertFalse(studentService.isValidNewStudent(student));
        studentRepo.delete(existingStudent);
        studentRepo.delete(student);
    }

    @Test
    void testCanAddSecondStudent() {
        studentRepo.save(student1);
        studentRepo.save(student2);
        assertFalse(studentService.isValidNewStudent(student1));
        assertFalse(studentService.isValidNewStudent(student2));
        studentRepo.delete(student2);
        studentRepo.delete(student1);
    }

    @Test
    void testStudentSaveByUid() {
        StudentDTO studentDTO = new StudentDTO(student1);
        StudentDTO result = studentService.saveByUid(studentDTO, student1Uid);
        assertEquals("name1", result.getName());
        Student studentResult = studentRepo.findByUid(student1Uid);
        assertEquals("name1", studentResult.getName());

        studentRepo.delete(studentResult);
        assertNull(studentService.findByUid(student1Uid));

    }

    @Transactional
    @Test
    void testUpdateStudentByUid() {
        StudentDTO studentDTO = new StudentDTO(student1);
        studentDTO.setName("Kevin");
        studentDTO.setAssignmentNum(9);
        studentDTO.setIde("Eclipse");
        studentDTO.setMentee("Bobby");
        studentDTO.setWillingToMentor(true);
        studentService.saveByUid(studentDTO, student1Uid);
        StudentDTO student2 = studentService.findByUid(student1Uid);
        assertEquals("Kevin", student2.getName());
        assertEquals(9, student2.getAssignmentNum());
        assertEquals("Eclipse", student2.getIde());
        assertEquals("Bobby", student2.getMentee());
        assertEquals(true, student2.getWillingToMentor());
    }

    @Transactional
    @Test
    void testFindAllAsDTOs() {

        Random random = new Random();
        String randomNumber1 = Integer.toString(random.nextInt());
        String randomNumber2 = Integer.toString(random.nextInt());

        student1.setName(randomNumber1);
        student2.setName(randomNumber2);

        studentRepo.save(student1);
        studentRepo.save(student2);

        List<Student> allStudents = studentRepo.findAll();

        List<StudentDTO> studentDTOs = studentService.findAllAsDTOs();

        int count = 0;

        for (StudentDTO studentDTO : studentDTOs) {
            if (randomNumber1.equals(studentDTO.getName()) || randomNumber2.equals(studentDTO.getName())) {
                count++;
            }
        }

        assertEquals(2, count);

    }

}
