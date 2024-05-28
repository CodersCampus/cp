package com.coderscampus.cp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;

@SpringBootTest
public class CheckinServiceTest {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CheckinService checkinService;
	@Autowired
	private CheckinRepository checkinRepo;
	
	@Test
	void testDeleteCheckin() {
		//Create UID 
		//Create new student with new UID
		//Save the student
		//Create new checkin
		//Save checkin
		//Instantiate a checkin DTO from a new checkin
		//Create second checkin object from checkin DTO
		//Confirm existence of second checkin in database
		//Delete second checkin
		//Confirm deletion from database
		//Clean up by deleting student from database
		
		
		
		
		String uid = UUID.randomUUID().toString();
		Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
		assertTrue(studentService.isValidNewStudent(student));
		studentRepo.delete(student);

	}


}
