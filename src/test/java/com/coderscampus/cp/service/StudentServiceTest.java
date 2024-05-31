package com.coderscampus.cp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
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
//        Creating a test to verify we're able to create and access students without sending the UID to front end for security reasons
		String uid = UUID.randomUUID().toString();
		Student existingStudent = new Student(uid, "Bobby", 17, "IntelliJ", false, "name", null); // Creates hypothetical existing student
		existingStudent = studentRepo.save(existingStudent); // Saves that existing student to the database
        StudentDTO studentDTO = new StudentDTO(existingStudent); // Sends studentDTO to the front-end because it does not have a Uid
        studentDTO.setName("Lucas");
        studentDTO.setAssignmentNum(12);
        studentDTO.setIde("Eclipse");
        studentDTO.setWillingToMentor(true);
        studentDTO.setMentee("Fred"); //updating studentDTO information

		Student student = new Student(studentDTO, uid); // Simulates sending updated DTO back to server side
        student.setUid(uid); // Sets Uid to student object created from studentDTO
        
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

}
