package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.LinkedInRepository;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LinkedInServiceTest {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentService studentService;

    @Autowired
    private LinkedInService linkedInService;
    @Autowired
    private LinkedInRepository linkedInRepo;

    Student student1;
    Student student2;

    StudentDTO studentDTO1;
    StudentDTO studentDTO2;

    String student1Uid;
    String student2Uid;

    List<CheckinDTO> student1CheckinDTOList;
    List<CheckinDTO> student2CheckinDTOList;

    List<StudentDTO> student1StudentDTOList;

    List<LinkedIn> student1LinkedInList;

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

        student1LinkedInList = new ArrayList<>();

        List<StudentDTO> student1StudentDTOList = new ArrayList<StudentDTO>();

        for (int i = 0; i < 4; i++) {
            LinkedIn linkedIn  = new LinkedIn();
            linkedIn.setBanner("Blocker" + i);
            linkedIn.setAbout("Blocker" + i);
            linkedIn.setUrl("Blocker" + i);
            linkedIn.setFeaturedPosts("Blocker" + i);
            linkedIn.setActivity("Blocker" + i);
            linkedIn.setSkills("Blocker" + i);
            linkedIn.setEmail("Blocker" + i);
            linkedIn.setFirstName("Blocker" + i);
            linkedIn.setLastName("Blocker" + i);
            linkedIn.setBiography("Blocker" + i);
            linkedIn.setEducation("Blocker" + i);
            linkedIn.setExperience("Blocker" + i);
            linkedIn.setLocation("Blocker" + i);
            linkedIn.setImage("Blocker" + i);
            linkedIn.setTitle("Blocker" + i);
            linkedIn.setStudent(student1);
            linkedIn.getStudent().setUid(student1Uid);
            linkedInRepo.save(linkedIn);
            student1LinkedInList.add(linkedIn);
        }
    }
    @AfterEach
    void cleanUpData() {
        student1LinkedInList.forEach(linkedIn -> {
            linkedInRepo.findById(linkedIn.getId()).ifPresent(linkedInRepo::delete);
        });
        studentRepo.delete(student1);
        studentRepo.delete(student2);
    }

    @Test
    @Transactional
    void testSetUpCreateLinkedIn() {
        assertEquals(4, student1LinkedInList.size());
    }

    @Test
	@Transactional
	void testSaveLinkedIn() {
        AtomicLong i = new AtomicLong(0L);
		student1LinkedInList.forEach(linkedInFromList -> {

            linkedInFromList.setBanner("Test");
			LinkedIn savedLinkedIn = linkedInService.save(linkedInFromList);
            assertTrue(savedLinkedIn.getId() > i.get());
            i.set(savedLinkedIn.getId());
            assertEquals(linkedInFromList.getBanner(), savedLinkedIn.getBanner());
            assertEquals(linkedInFromList.getStudent().getId(), savedLinkedIn.getStudent().getId());
		});
	}


//    @Test
//    @Transactional
//    void testSaveByUidForNullUID() {
//        student1LinkedInList.forEach(linkedIn -> {
//            LinkedIn linkedInUt = linkedInService.saveByUid(linkedIn, null);
//            assertNull(linkedInUt);
//        });
//    }

    @Test
    @Transactional
    void testSaveByUidForValidUID() {
        String goodUid = student1Uid;
        student1LinkedInList.forEach(linkedIn -> {
            LinkedIn linkedInUt = linkedInService.saveByUid(linkedIn, goodUid);
             assertNotNull(linkedInUt);
        });
    }

//    @Test
//    @Transactional
//    void testSaveByUidForInvalidUID() {
//        String badUid = "abc";
//        student1LinkedInList.forEach(linkedIn -> {
//            LinkedIn linkedInUt = linkedInService.saveByUid(linkedIn, badUid);
//            assertNull(linkedInUt);
//        });
//    }

    @Test
	@Transactional
	void testFindByLinkedInId() {
		student1LinkedInList.forEach(linkedIn -> {
            assertEquals(linkedIn, linkedInService.findById(linkedIn.getId()));
        });
	}

    @Test
	@Transactional
	void testFindByLinkedInIdWhenLinkedInIdIsOutOfBounds() {
        assertThrows(NoSuchElementException.class, () -> {
            linkedInService.findById(Long.MAX_VALUE);
        });
	}

//    @Test
//	@Transactional
//	void testFindByLinkedInIdWhenLinkedInIdIsNull() {
//        assertThrows(null, () -> {
//            linkedInService.findById(null);
//        });
//	}

//    @Test
//    @Transactional
//    void testDeleteWhenUidIsNull() {
//        student1LinkedInList.forEach(linkedIn -> {
//            LinkedIn foundLinkedIn = linkedInRepo.findById(linkedIn.getId()).orElse(null);
//            assertNotNull(foundLinkedIn);
//
//            //if we want this to work, we will need to change the return type for delete to long
//            //Long deleted = linkedInService.delete(linkedIn, null);
//            foundLinkedIn = linkedInRepo.findById(linkedIn.getId()).orElse(null);
//            assertNotNull(foundLinkedIn);
//            //assertEquals(0L, deleted);
//        });
//    }

    @Test
    @Transactional
    void testDeleteWhenLinkedInIDIsInvalid() {
        Random random = new Random();
        Long wrongId = (long) (random.nextInt(1000) + 1);
        student1LinkedInList.forEach(linkedIn -> {
            Long originalId = linkedIn.getId();
            LinkedIn foundLinkedIn = linkedInRepo.findById(originalId).orElse(null);
            assertNotNull(foundLinkedIn);
            linkedIn.setId(wrongId);

            //Long deleted = linkedInService.delete(linkedIn);
            //replaced because it returns void
            linkedInService.delete(linkedIn);
            foundLinkedIn = linkedInRepo.findById(originalId).orElse(null);
            assertNotNull(foundLinkedIn);

            //assertEquals(0L, deleted);
            linkedIn.setId(originalId);
        });
    }

    @Test
    @Transactional
    void testDeleteWhenLinkedInIDIsValid() {
        student1LinkedInList.forEach(linkedIn -> {
            Long originalId = linkedIn.getId();
            LinkedIn storedLinkedIn = linkedInRepo.findById(originalId).orElse(null);
            assertNotNull(storedLinkedIn);

            linkedInService.delete(linkedIn);
            LinkedIn foundLinkedIn = linkedInRepo.findById(originalId).orElse(null);
            assertNull(foundLinkedIn);

            linkedIn = storedLinkedIn;

        });
    }



}
