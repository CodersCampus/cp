package com.coderscampus.cp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;

import jakarta.transaction.Transactional;

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
	@BeforeEach
	void cleanUpData() {
		checkinRepo.deleteAll();
		studentRepo.deleteAll();
	}

	@Test
	@Transactional
	void testDeleteCheckin() {
		// Create UID
		String uid = UUID.randomUUID().toString();
		// Create new student with new UID
		Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
		// Save the student
		studentRepo.save(student);
		// Create new checkin
		Checkin checkin = new Checkin(uid, null, 9, true, "assignment9", student, Checkin.Role.CODER,
				Checkin.CodingType.CRUD);
		// Save checkin
		// Instantiate a checkin DTO from a new checkin
		CheckinDTO checkinDTO = checkinService.saveByUid(new CheckinDTO(checkin), uid);

		// Create second checkin object from checkin DTO
		Checkin foundCheckin = new Checkin(checkinDTO, uid);
		// Confirm existence of second checkin in database
		assertTrue(checkinRepo.findById(foundCheckin.getId()).isPresent());
		// Delete second checkin
		checkinRepo.delete(foundCheckin);
		// Confirm deletion from database
		assertFalse(checkinRepo.findById(foundCheckin.getId()).isPresent());
		// Clean up by deleting student from database
		studentRepo.delete(student);
	}

	@Test
	@Transactional
	void testSaveByUid() {
		// Create UID
		String uid = UUID.randomUUID().toString();
		// Create new student with new UID
		Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
		// Save the student
		studentRepo.save(student);
		// Create new checkin
		Checkin checkin = new Checkin(uid, null, 9, true, "assignment9", student, Checkin.Role.CODER,
				Checkin.CodingType.CRUD);
		// Save checkin
		// Instantiate a checkin DTO from a new checkin

		CheckinDTO checkinDTO = checkinService.saveByUid(new CheckinDTO(checkin), uid);

		// Create second checkin object from checkin DTO
		Checkin foundCheckin = new Checkin(checkinDTO, uid);
		// Confirm existence of second checkin in database
		assertTrue(checkinRepo.findById(foundCheckin.getId()).isPresent());
		// Change all relevant (non-id, uid, and creation date) fields in checkin
		checkinDTO.setBlockers(false);
		checkinDTO.setRole(Checkin.Role.OBSERVER);
		checkinDTO.setBlockerDescription("Blep");
		checkinDTO.setCodingType(Checkin.CodingType.CODE_REVIEW);
		checkinDTO.setNextAssignment(11);
		// Save by uid against the change
		checkinService.saveByUid(checkinDTO, uid);
		// Get the changed record
		List<CheckinDTO> checkinList = checkinService.findByUid(uid);
		CheckinDTO changedCheckin = null;
		for (CheckinDTO checkin1 : checkinList) {
			if (checkinDTO.getId().equals(checkin1.getId())) {
				changedCheckin = checkin1;
			}
		}
		// verify that all fields changed
		assertEquals(false, changedCheckin.getBlockers());
		assertEquals("Blep", changedCheckin.getBlockerDescription());
		assertEquals(11, changedCheckin.getNextAssignment());
		assertEquals(Checkin.Role.OBSERVER, changedCheckin.getRole());
		assertEquals(Checkin.CodingType.CODE_REVIEW, changedCheckin.getCodingType());
		// Delete second checkin
		// Start here

		checkinService.delete(checkinDTO, uid);
		// Confirm deletion from database
		assertFalse(checkinRepo.findById(changedCheckin.getId()).isPresent());
		// Clean up by deleting student from database
		studentRepo.delete(student);
	}

	@Test
	@Transactional
	void testFindByUid() {
		// Create UID
		String uid = UUID.randomUUID().toString();
		String uid2 = UUID.randomUUID().toString();
		// Create new student with new UID
		Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
		// Save the student
		studentRepo.save(student);
//        studentRepo.save(student2);
		// Create new checkin
		Checkin checkin = new Checkin(uid, null, 9, true, "assignment9", student, Checkin.Role.CODER,
				Checkin.CodingType.CRUD);
		Checkin checkin2 = new Checkin(uid, null, 9, true, "assignment10", student, Checkin.Role.CODER,
				Checkin.CodingType.CRUD);
		// Save checkin
		CheckinDTO checkinDTO1 = checkinService.saveByUid(new CheckinDTO(checkin), uid);
		CheckinDTO checkinDTO2 = checkinService.saveByUid(new CheckinDTO(checkin2), uid);
//
		List<CheckinDTO> checkinDTOs = checkinService.findByUid(uid);
		// since using checkinService.findByUid in prev tests, index is not starting at 0
		// could use streams or arrraylist to have better looking code but it gets job done
		String ids = "";
		for (CheckinDTO checkinDTO : checkinDTOs) {
			ids = ids + checkinDTO.getId();
		}
		assertTrue(ids.contains("" + checkinDTO1.getId()));
		assertEquals(2, checkinDTOs.size());

		String assignment = "";
		for (CheckinDTO checkinDTO : checkinDTOs) {
			assignment = assignment + checkinDTO.getNextAssignment();
		}
		assertTrue(assignment.contains("" + checkinDTO1.getNextAssignment()));
		
		String assignment2 = "";
		for (CheckinDTO checkinDTO : checkinDTOs) {
			assignment2 = assignment2 + checkinDTO.getNextAssignment();
		}
		assertTrue(assignment.contains("" + checkinDTO2.getNextAssignment()));

	}

	}
	


