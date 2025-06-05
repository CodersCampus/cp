package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.transaction.Transactional;
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

    @Test
    void testIsValidStudentUpdate() {
        String uid = UUID.randomUUID().toString();
        Student existingStudent = new Student(uid, "Bobby", 17, "IntelliJ", false, "name", null);
        existingStudent = studentRepo.save(existingStudent);
        StudentDTO studentDTO = new StudentDTO(existingStudent);
        studentDTO.setName("Lucas");
        studentDTO.setAssignmentNum(12);
        studentDTO.setIde("Eclipse");
        studentDTO.setWillingToMentor(true);
        studentDTO.setMentee("Fred");
        Student student = new Student(studentDTO, uid);
        student.setUid(uid);

        assertTrue(studentService.doesStudentExistInRepository(student));
        assertEquals(existingStudent.getId(), student.getId());
        assertEquals(student.getName(), "Lucas");
        assertEquals(student.getAssignmentNum(), 12);
        assertEquals(student.getIde(), "Eclipse");
        assertEquals(student.getWillingToMentor(), true);
        assertEquals(student.getMentee(), "Fred");

        studentRepo.delete(existingStudent);
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
        String uid = UUID.randomUUID().toString();
        String uid2 = UUID.randomUUID().toString();
        Student student2 = new Student(uid2, "Bobby", 12, "IntelliJ", false, "name", null);
        Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
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
        Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
        StudentDTO studentDTO = new StudentDTO(student);
        StudentDTO result = studentService.saveByUid(studentDTO, uid);
        assertEquals("Bobby", result.getName());
        Student studentResult = studentRepo.findByUid(uid);
        assertEquals("Bobby", studentResult.getName());

        studentRepo.delete(studentResult);
        assertNull(studentService.findByUid(uid));

    }

    @Transactional
    @Test
    void testUpdateStudentByUid() {
        String uid = UUID.randomUUID().toString();
        Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
        StudentDTO studentDTO = new StudentDTO(student);
        studentDTO.setName("Kevin");
        studentDTO.setAssignmentNum(9);
        studentDTO.setIde("Eclipse");
        studentDTO.setMentee("Bobby");
        studentDTO.setWillingToMentor(true);
        studentService.saveByUid(studentDTO, uid);
        StudentDTO student2 = studentService.findByUid(uid);
        assertEquals("Kevin", student2.getName());
        assertEquals(9, student2.getAssignmentNum());
        assertEquals("Eclipse", student2.getIde());
        assertEquals("Bobby", student2.getMentee());
        assertEquals(true, student2.getWillingToMentor());
    }

    @Transactional
    @Test
    void testFindAllAsDTOs() {
        Student student1 = new Student();
        Student student2 = new Student();

        student1.setUid(UUID.randomUUID().toString());
        student2.setUid(UUID.randomUUID().toString());

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

        studentRepo.delete(student1);
        studentRepo.delete(student2);

    }

}
