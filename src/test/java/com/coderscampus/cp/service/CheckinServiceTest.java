package com.coderscampus.cp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.dto.CheckinDTO;
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
        String uid = UUID.randomUUID().toString();
		//Create new student with new UID
        Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
		//Save the student
        studentRepo.save(student);
		//Create new checkin
        Checkin checkin = new Checkin( 1L, uid, null, 9, true, "assignment9", student, Checkin.Role.CODER, Checkin.CodingType.CRUD);
		//Save checkin
        checkinRepo.save(checkin);
		//Instantiate a checkin DTO from a new checkin
        CheckinDTO checkinDTO = new CheckinDTO(checkin);

		//Create second checkin object from checkin DTO
        Checkin foundCheckin = new Checkin(checkinDTO, uid);
		//Confirm existence of second checkin in database
        //Test failed
        assertTrue(checkinRepo.findById(foundCheckin.getId()).isPresent());
		//Delete second checkin
		//Confirm deletion from database
		//Clean up by deleting student from database
		
		
		
		


		assertTrue(studentService.isValidNewStudent(student));
		studentRepo.delete(student);

	}


}
