package com.coderscampus.cp.service;

import java.util.List;
import java.util.UUID;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.dto.CheckinDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;

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

//    Consider for later, more streamlined
//    @BeforeEach
//    void setUpBeforeClass() throws Exception {}
	
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
        assertTrue(checkinRepo.findById(foundCheckin.getId()).isPresent());
		//Delete second checkin
        checkinRepo.delete(foundCheckin);
		//Confirm deletion from database
        assertFalse(checkinRepo.findById(foundCheckin.getId()).isPresent());
		//Clean up by deleting student from database
        studentRepo.delete(student);
	}

    @Test
    void testSaveByUid(){
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
        assertTrue(checkinRepo.findById(foundCheckin.getId()).isPresent());
        //Change all relevant (non-id, uid, and creation date) fields in checkin
        checkinDTO.setBlockers(false);
        checkinDTO.setRole(Checkin.Role.OBSERVER);
        checkinDTO.setBlockerDescription("Blep");
        checkinDTO.setCodingType(Checkin.CodingType.CODE_REVIEW);
        checkinDTO.setNextAssignment(11);
        //Save by uid against the change
        checkinService.saveByUid(checkinDTO, uid);
        //Get the changed record
        List<CheckinDTO> checkinList = checkinService.findByUid(uid);
        CheckinDTO changedCheckin = null;
        for (CheckinDTO checkin1 : checkinList) {
            if (checkinDTO.getId().equals(checkin1.getId())) {
                changedCheckin = checkin1;
            }
        }
        //verify that all fields changed
        assertEquals(false, changedCheckin.getBlockers());
        assertEquals("Blep", changedCheckin.getBlockerDescription());
        assertEquals(11, changedCheckin.getNextAssignment());
        assertEquals(Checkin.Role.OBSERVER, changedCheckin.getRole());
        assertEquals(Checkin.CodingType.CODE_REVIEW, changedCheckin.getCodingType());
        //Delete second checkin
        //Start here

        checkinService.delete(checkinDTO, uid);
        //Confirm deletion from database
        assertFalse(checkinRepo.findById(changedCheckin.getId()).isPresent());
        //Clean up by deleting student from database
        studentRepo.delete(student);
    }
    @Test
    void testFindByUid(){
    	//Create UID
        String uid = UUID.randomUUID().toString();
        String uid2 = UUID.randomUUID().toString();
        //Create new student with new UID
        Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
        Student student2 = new Student(uid2, "Bobby", 12, "IntelliJ", false, "name", null);
        //Save the student
        studentRepo.save(student);
        studentRepo.save(student2);
        //Create new checkin
        //Please fix the checkin constructor so that an id is not required
        Checkin checkin = new Checkin( 1L, uid, null, 9, true, "assignment9", student, Checkin.Role.CODER, Checkin.CodingType.CRUD);
        Checkin checkin2 = new Checkin( 2L, uid2, null, 9, true, "assignment10", student2, Checkin.Role.CODER, Checkin.CodingType.CRUD);
        //Save checkin
        checkinRepo.save(checkin);
        checkinRepo.save(checkin2);
        System.out.println(checkin);
        System.out.println(checkin2);
        System.out.println(student);
        System.out.println(student2);
        //please note that checkins are not listed in students above
        List<CheckinDTO> checkinDTOs = checkinService.findByUid(uid);
        for(CheckinDTO checkinDTO: checkinDTOs) {
        	assertEquals(checkin.getId(), checkinDTO.getId());
        }
        //start here
        
    }
}
