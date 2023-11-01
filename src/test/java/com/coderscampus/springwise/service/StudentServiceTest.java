package com.coderscampus.springwise.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coderscampus.springwise.domain.Student;
import com.coderscampus.springwise.repository.StudentRepository;

@SpringBootTest
class StudentServiceTest {
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentRepository studentRepo;

	@Test
	void testIsValidStudentUpdate() {
		
		String uid = UUID.randomUUID().toString();
		Student existingStudent = new Student(0, "bobby", 17, "myHandle", "1", uid);
		existingStudent = studentRepo.save(existingStudent);
		Student student = new Student(existingStudent.getId(), "bobby", 12, "myHandle", "1", uid);
		assertTrue(studentService.isValidStudentUpdateOrDelete(student));
		

		String uid2 = UUID.randomUUID().toString();
		Student studentTwo = new Student(existingStudent.getId(), "kate", 12, "yourHandle", "2", uid2);
		assertFalse(studentService.isValidNewStudent(studentTwo));
		
		studentRepo.delete(existingStudent);
	}

	@Test
	void testIsValidNewStudent() {
		String uid = UUID.randomUUID().toString();
		Student student = new Student(0, "bobby", 12, "myHandle", "1", uid);
		assertTrue(studentService.isValidNewStudent(student));
		studentRepo.delete(student);
		
	}
	
	@Test
	void testValidNewStudentIfExists() {
		String uid = UUID.randomUUID().toString();
		Student student = new Student(0, "bobby", 12, "myHandle", "1", uid);
		Student existingStudent = new Student(0, "bobby", 17, "myHandle", "1", uid);
		studentRepo.save(existingStudent);
		assertFalse(studentService.isValidNewStudent(student));
		studentRepo.delete(existingStudent);
		studentRepo.delete(student);
		
	}

	@Test
	void testDeleteIfAllowed() {
		int id = (int) Math.round(Math.random() * 10000);
		String uid = UUID.randomUUID().toString();
		Student student = new Student(id, "bobby", 12, "myHandle", "1", uid);
		Student existingStudent = new Student(id, "bobby", 17, "myHandle", "1", uid);
		studentRepo.save(existingStudent);
		assertTrue(studentService.delete(student));
		
		
	}

	@Test
	void testDeleteIfNotAllowed() {
		
		int id = (int) Math.round(Math.random() * 10000);
		String uid = UUID.randomUUID().toString();
		String uid2 = UUID.randomUUID().toString();
		Student student = new Student(id, "bobby", 12, "myHandle", "1", uid);
		Student existingStudent = new Student(id, "bobby", 17, "myHandle", "1", uid2);
		studentRepo.save(student);
		
		assertFalse(studentService.delete(existingStudent));
	}


}
