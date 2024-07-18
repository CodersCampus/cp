package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	Student student1;
	Student student2;

	StudentDTO studentDTO1;
	StudentDTO studentDTO2;

	String student1Uid;
	String student2Uid;

	List<CheckinDTO> student1CheckinDTOList;
	List<CheckinDTO> student2CheckinDTOList;

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

		student1CheckinDTOList = new ArrayList<>();
		student2CheckinDTOList = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			Checkin checkin = new Checkin();
			checkin.setBlockerDescription("Blocker" + i);
			checkin.setNextAssignment(i);
			checkin.setBlockers(true);
			checkin.setRole(Checkin.Role.CODER);
			checkin.setCodingType(Checkin.CodingType.CRUD);
			checkin.setStudent(student1);
			checkin.setUid(student1Uid);
			checkinRepo.save(checkin);
			CheckinDTO checkinDTO = new CheckinDTO(checkin);
			student1CheckinDTOList.add(checkinDTO);
		}
	}

	@AfterEach
	void cleanUpData() {
		student1CheckinDTOList.forEach(checkinDTO -> {
			Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
			checkinRepo.delete(checkin);
		});
		student2CheckinDTOList.forEach(checkinDTO -> {
			Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
			checkinRepo.delete(checkin);
		});
		studentRepo.delete(student1);
		studentRepo.delete(student2);
	}

	@Test
	@Transactional
	void testSetUpCreateCheckIn() {
		assertEquals(4, student1CheckinDTOList.size());
	}

	@Test
	@Transactional
	void testNullUID() {
		student1CheckinDTOList.forEach(checkinDTO -> {
			CheckinDTO checkinDTOUt = checkinService.saveByUid(checkinDTO, null);
			assertNull(checkinDTOUt);
		});
	}

	@Test
	@Transactional
	void testValidUID() {
		String goodUid = student1Uid;
		student1CheckinDTOList.forEach(checkinDTO -> {
			CheckinDTO checkinDTOUt = checkinService.saveByUid(checkinDTO, goodUid);
			assertNotNull(checkinDTOUt);
		});
	}

	@Test
	@Transactional
	void testInvalidUID() {
		String badUid = "abc";
		student1CheckinDTOList.forEach(checkinDTO -> {
			CheckinDTO checkinDTOUt = checkinService.saveByUid(checkinDTO, badUid);
			assertNull(checkinDTOUt);
		});
	}

	@Test
	@Transactional
	void testFindByUIDWhenNull() {
		List<CheckinDTO> checkinDTOListUt = checkinService.findByUid(null);
		assertEquals(0, checkinDTOListUt.size());
	}

	@Test
	@Transactional
	void testFindByUIDWhenInvalid() {
		String badUid = "abc";
		List<CheckinDTO> checkinDTOListUt = checkinService.findByUid(badUid);
		assertEquals(0, checkinDTOListUt.size());
	}

	@Test
	@Transactional
	void testFindByUIDWhenUpdatedWithExistingUID() {
		student1CheckinDTOList.forEach(checkinDTO -> {
			checkinDTO.setBlockerDescription(checkinDTO.getBlockerDescription() + " updated");
			checkinService.saveByUid(checkinDTO, student1Uid);
		});
		List<CheckinDTO> checkinDTOListUt = checkinService.findByUid(student1Uid);
		checkinDTOListUt.forEach(checkinDTO -> {
			assertTrue(checkinDTO.getBlockerDescription().endsWith(" updated"));
		});
	}

	@Test
	@Transactional
	void testFindByUIDWhenWrongUIDSent() {
		List<CheckinDTO> checkinDTOListUt = checkinService.findByUid(student2Uid);
		assertEquals(0, checkinDTOListUt.size());
	}

	@Test
	@Transactional
	void testUpdateWithExistingUID() {
		student1CheckinDTOList.forEach(checkinDTO -> {

			checkinDTO.setBlockers(false);
			checkinDTO.setRole(Checkin.Role.OBSERVER);
			checkinDTO.setBlockerDescription("Blep");
			checkinDTO.setCodingType(Checkin.CodingType.CODE_REVIEW);
			checkinDTO.setNextAssignment(11);

			CheckinDTO checkinDTOUt = checkinService.saveByUid(checkinDTO, student1Uid);

			assertEquals(false, checkinDTOUt.getBlockers());
			assertEquals("Blep", checkinDTOUt.getBlockerDescription());
			assertEquals(11, checkinDTOUt.getNextAssignment());
			assertEquals(Checkin.Role.OBSERVER, checkinDTOUt.getRole());
			assertEquals(Checkin.CodingType.CODE_REVIEW, checkinDTOUt.getCodingType());
		});
	}

	@Test
	@Transactional
	void testUpdateWithWrongUID() {
		String wrongUid = student2Uid;
		student1CheckinDTOList.forEach(checkinDTO -> {

			checkinDTO.setBlockers(false);
			checkinDTO.setRole(Checkin.Role.OBSERVER);
			checkinDTO.setBlockerDescription("Blep");
			checkinDTO.setCodingType(Checkin.CodingType.CODE_REVIEW);
			checkinDTO.setNextAssignment(11);

			CheckinDTO checkinDTOUt = checkinService.saveByUid(checkinDTO, wrongUid);

			assertNull(checkinDTOUt);
		});
	}

	// Start here for CheckinService.findAll() test cases

	@Test
	@Transactional
	void testFindAllCorrectCountOfAddedCheckins() {

		// check the initial count
		int start = student1CheckinDTOList.size();
		int size = checkinService.findAll().size();
		assertTrue(size >= start);
		// add 2 checkin
		for (int i = 0; i < 2; i++) {
			Checkin checkin = new Checkin();
			checkin.setBlockerDescription("Blocker" + i);
			checkin.setNextAssignment(i);
			checkin.setBlockers(true);
			checkin.setRole(Checkin.Role.CODER);
			checkin.setCodingType(Checkin.CodingType.CRUD);
			checkin.setStudent(student1);
			checkin.setUid(student1Uid);
			checkinRepo.save(checkin);
			CheckinDTO checkinDTO = new CheckinDTO(checkin);
			student1CheckinDTOList.add(checkinDTO);
		}
		// check the count = 6
		assertEquals(size + 2, checkinService.findAll().size());
	}

	@Test
	@Transactional
	void testFindAllCorrectCountOfRemovedCheckins() {
		// get the initial size from findAll
		int size = checkinService.findAll().size();
        List<CheckinDTO> checkinDTOsToRemove = new ArrayList<>(student1CheckinDTOList);
		int i = 0;
		// remove 2 of student1CheckinDTOList data from checkinRepo
		for (CheckinDTO checkinDTO : checkinDTOsToRemove) {
			i++;
			if (i > 2) {
				break;
			}
			Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
			checkinRepo.delete(checkin);
			student1CheckinDTOList.remove(checkinDTO);
		}
		// assert the new size from findAll is 2 less the initial size
		int newSize = checkinService.findAll().size();
		assertEquals(size - 2, newSize);

	}

	@Test
	@Transactional
	void testFindAllCorrectCountOfUpdatedCheckins() {
        // get the initial size from findAll
        int size = checkinService.findAll().size();
        List<CheckinDTO> checkinDTOsToUpdate = new ArrayList<>(student1CheckinDTOList);
        int i = 0;
        // update 2 of student1CheckinDTOList data from checkinRepo
        for (CheckinDTO checkinDTO : checkinDTOsToUpdate) {
            i++;
            if (i > 2) {
                break;
            }
            Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
            checkin.setRole(Checkin.Role.OBSERVER);
            checkinRepo.save(checkin);
        }
        assertEquals(size, checkinService.findAll().size());
	}

	@Test
	@Transactional
	void testFindAllUpdateReallyHappened() {
        // get the initial size from findAll
        int size = checkinService.findAll().size();
        List<CheckinDTO> checkinDTOsToUpdate = new ArrayList<>(student1CheckinDTOList);
        int i = 0;
        // update 2 of student1CheckinDTOList data from checkinRepo
        for (CheckinDTO checkinDTO : checkinDTOsToUpdate) {
            i++;
            if (i > 2) {
                break;
            }
            Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
            checkin.setBlockerDescription("I'm Stuck on the while loop");
            checkinRepo.save(checkin);
        }
        int j = 0;
        //work from here next time. need a list
        for (CheckinDTO checkinDTO : student1CheckinDTOList) {
            if(checkinDTO.getBlockerDescription().equals("I'm Stuck on the while loop")) {
                j++;
            }
        }
        assertEquals(2, j);
    }

	@Test
	@Transactional
	void testFindAllCheckinsAreReturnedInDescendingOrderByDate() {

	}

	// Everything below this is abandoned for now to be replaced

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
//        Checkin foundCheckin = new Checkin(checkinDTO, uid);
//        // Confirm existence of second checkin in database
//        assertTrue(checkinRepo.findById(foundCheckin.getId()).isPresent());
//        // Delete second checkin
//        checkinRepo.delete(foundCheckin);
//        // Confirm deletion from database
//        assertFalse(checkinRepo.findById(foundCheckin.getId()).isPresent());
//        // Clean up by deleting student from database
//        studentRepo.delete(student);
	}

	@Test
	@Transactional
//    This will fail if "spring.jpa.hibernate.ddl-auto=create-drop" is changed in application.properties
	void testFindAll() {
//        Create 2 new students
		String uid1 = UUID.randomUUID().toString();
		String uid2 = UUID.randomUUID().toString();
		// Create new student with new UID
		Student student1 = new Student(uid1, "Bobby", 12, "IntelliJ", false, "name", null);
		Student student2 = new Student(uid2, "Fred", 12, "IntelliJ", false, "name", null);
//        Save students
		studentRepo.save(student1);
		studentRepo.save(student2);
//        Create 2 new checkins for each student
		Checkin checkin1 = new Checkin(uid1, null, 9, true, "assignment9", student1, Checkin.Role.CODER,
				Checkin.CodingType.CRUD);
		Checkin checkin2 = new Checkin(uid1, null, 12, true, "assignment10", student1, Checkin.Role.CODER,
				Checkin.CodingType.CRUD);
		Checkin checkin3 = new Checkin(uid2, null, 13, true, "assignment9", student2, Checkin.Role.CODER,
				Checkin.CodingType.CRUD);
		Checkin checkin4 = new Checkin(uid2, null, 14, true, "assignment10", student2, Checkin.Role.CODER,
				Checkin.CodingType.CRUD);
//        Save the checkins
		CheckinDTO checkinDTO1 = checkinService.saveByUid(new CheckinDTO(checkin1), uid1);
		CheckinDTO checkinDTO2 = checkinService.saveByUid(new CheckinDTO(checkin2), uid1);
		CheckinDTO checkinDTO3 = checkinService.saveByUid(new CheckinDTO(checkin3), uid2);
		CheckinDTO checkinDTO4 = checkinService.saveByUid(new CheckinDTO(checkin4), uid2);
//        call findAll()
		List<CheckinDTO> checkinDTOs = checkinService.findAll();
//        Assert that the size is correct
		String ids = "";
		for (CheckinDTO checkinDTO : checkinDTOs) {
			ids = ids + checkinDTO.getId();
		}
		assertTrue(ids.contains("" + checkinDTO1.getId()));
		assertTrue(ids.contains("" + checkinDTO2.getId()));
		assertTrue(ids.contains("" + checkinDTO3.getId()));
		assertTrue(ids.contains("" + checkinDTO4.getId()));
		assertEquals(4, checkinDTOs.size());
//        keep testing properties of checkins
		String assignment1 = "";
		String assignment2 = "";
		String assignment3 = "";
		String assignment4 = "";
		for (CheckinDTO checkinDTO : checkinDTOs) {
			assignment4 = assignment4 + checkinDTO.getNextAssignment();
			assignment3 = assignment3 + checkinDTO.getNextAssignment();
			assignment2 = assignment2 + checkinDTO.getNextAssignment();
			assignment1 = assignment1 + checkinDTO.getNextAssignment();
		}

		assertTrue(assignment1.contains("" + checkinDTO1.getNextAssignment()));
		assertTrue(assignment2.contains("" + checkinDTO2.getNextAssignment()));
		assertTrue(assignment3.contains("" + checkinDTO3.getNextAssignment()));
		assertTrue(assignment4.contains("" + checkinDTO4.getNextAssignment()));
	}

	@Test
	@Transactional
	void testFindById() {
		// Create UID
		String uid = UUID.randomUUID().toString();
		// Create new student with new UID
		Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
		// Save the student
		studentRepo.save(student);
		assertNotNull(student.getId());
		// Create new checkin
		Checkin checkin = new Checkin(uid, null, 9, true, "assignment9", student, Checkin.Role.CODER,
				Checkin.CodingType.CRUD);
		Checkin checkin2 = new Checkin(uid, null, 11, true, "assignment10", student, Checkin.Role.CODER,
				Checkin.CodingType.CRUD);
		// Save checkin
		CheckinDTO checkinDTO1 = checkinService.saveByUid(new CheckinDTO(checkin), uid);
		CheckinDTO checkinDTO2 = checkinService.saveByUid(new CheckinDTO(checkin2), uid);
		// Check find by id
		CheckinDTO foundCheckinDTO = checkinService.findById(checkinDTO1.getId(), uid);
		assertEquals("assignment9", foundCheckinDTO.getBlockerDescription());
		CheckinDTO foundCheckinDTO2 = checkinService.findById(checkinDTO2.getId(), uid);
		assertEquals("assignment10", foundCheckinDTO2.getBlockerDescription());
	}
}
