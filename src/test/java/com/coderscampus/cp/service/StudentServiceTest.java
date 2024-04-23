package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class StudentServiceTest {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepo;

    @Test
    void testIsValidStudentUpdate() {
        String uid = UUID.randomUUID().toString();
        Student existingStudent = new Student(0, "bobby", 17, uid);
        existingStudent = studentRepo.save(existingStudent);
        Student student = new Student(existingStudent.getId(), "bobby", 12, uid);
        assertTrue(studentService.isValidStudentUpdateOrDelete(student));
        String uid2 = UUID.randomUUID().toString();
        Student studentTwo = new Student(existingStudent.getId(), "kate", 12, uid2);
        assertFalse(studentService.isValidNewStudent(studentTwo));
        studentRepo.delete(existingStudent);
    }

//    @Test
//    void testIsValidStudentUpdate() {
//        String uid = UUID.randomUUID().toString();
//        Student existingStudent = new Student(1, uid, "Dave Naugler", 12);
//        System.out.println("EXISTINGSTUDENT: " + existingStudent);
//        existingStudent = studentRepo.save(existingStudent);
//        Student student = new Student(existingStudent.getId(), uid, "Dave Naugler", 12);
//        assertTrue(studentService.isValidStudentUpdateOrDelete(student));
//        String uid2 = UUID.randomUUID().toString();
//        Student studentTwo = new Student(existingStudent.getId(), uid, "Kate CrossFit", 5);
//        assertFalse(studentService.isValidNewStudent(studentTwo));
//        studentRepo.delete(existingStudent);
//    }


    @Test
    void testIsValidNewStudent() {
        String uid = UUID.randomUUID().toString();
        Student student = new Student(0, "bobby", 12, uid);
        studentService.saveByUid(student,uid);
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


//	@Test
//	void testDeleteIfAllowed() {
//		int id = (int) Math.round(Math.random() * 10000);
//		String uid = UUID.randomUUID().toString();
//		Student student = new Student(id, "bobby", 12, "myHandle", "1", uid);
//		Student existingStudent = new Student(id, "bobby", 17, "myHandle", "1", uid);
//		studentRepo.save(existingStudent);
//		assertTrue(studentService.delete(student));
//	}


    //	@Test
//	void testDeleteIfNotAllowed() {
//		int id = (int) Math.round(Math.random() * 10000);
//		String uid = UUID.randomUUID().toString();
//		String uid2 = UUID.randomUUID().toString();
//		Student student = new Student(id, "bobby", 12, "myHandle", "1", uid);
//		Student existingStudent = new Student(id, "bobby", 17, "myHandle", "1", uid2);
//		studentRepo.save(student);
//		assertFalse(studentService.delete(existingStudent));
//	}
    @Test
    void testCanAddSecondStudent() {
        String uid = UUID.randomUUID().toString();
        String uid2 = UUID.randomUUID().toString();
        Student student2 = new Student(1, "bobby", 12, uid2);
        Student student = new Student(0, "bobby", 12, uid);
//    Student existingStudent = new Student(0, "bobby", 17, uid);
//    studentRepo.save(existingStudent);
        studentRepo.save(student);
        studentRepo.save(student2);
//    assertFalse(studentService.isValidNewStudent(student));
        studentRepo.delete(student2);
        studentRepo.delete(student);
    }


}
