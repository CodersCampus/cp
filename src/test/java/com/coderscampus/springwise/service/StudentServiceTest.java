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

		fail("Not yet implemented");
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


}
