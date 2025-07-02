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
            linkedIn.setBanner(true);
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

            linkedInFromList.setBanner(true);
			LinkedIn savedLinkedIn = linkedInService.saveByUid(linkedInFromList, student1Uid);
            assertTrue(savedLinkedIn.getId() > i.get());
            i.set(savedLinkedIn.getId());
            assertEquals(linkedInFromList.getBanner(), savedLinkedIn.getBanner());
            assertEquals(linkedInFromList.getStudent().getId(), savedLinkedIn.getStudent().getId());
		});
	}


    @Test
    @Transactional
    void testSaveByUidForNullUID() {
        student1LinkedInList.forEach(linkedIn -> {
            LinkedIn linkedInUt = linkedInService.saveByUid(linkedIn, null);
            assertNull(linkedInUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForValidUID() {
        String goodUid = student1Uid;
        student1LinkedInList.forEach(linkedIn -> {
            LinkedIn linkedInUt = linkedInService.saveByUid(linkedIn, goodUid);
             assertNotNull(linkedInUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForInvalidUID() {
        String badUid = "abc";
        student1LinkedInList.forEach(linkedIn -> {
            LinkedIn linkedInUt = linkedInService.saveByUid(linkedIn, badUid);
            assertNull(linkedInUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForUpdateWithWrongUID() {
        String wrongUid = student2Uid;
        student1LinkedInList.forEach(linkedIn -> {

            LinkedIn linkedInUt = linkedInService.saveByUid(linkedIn, wrongUid);
            assertNull(linkedInUt);
        });
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfAddedLinkedIns() {

        int start = student1LinkedInList.size();
        int size = linkedInService.findAll().size();
        assertTrue(size >= start);
        for (int i = 0; i < 2; i++) {
            LinkedIn linkedIn = new LinkedIn();
            linkedIn.setStudent(student1);
            linkedIn.setBanner(true);
            linkedInRepo.save(linkedIn);
            LinkedIn newLinkedIn = linkedIn;
            student1LinkedInList.add(newLinkedIn);
        }
        assertEquals(size + 2, linkedInService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfRemovedLinkedIns() {
        int size = linkedInService.findAll().size();
        List<LinkedIn> linkedInsToRemove = new ArrayList<>(student1LinkedInList);
        int i = 0;
        for (LinkedIn linkedIn : linkedInsToRemove) {
            i++;
            if (i > 2) {
                break;
            }
            LinkedIn newLinkedIn = linkedInRepo.findById(linkedIn.getId()).get();
            linkedInRepo.delete(linkedIn);
            student1LinkedInList.remove(linkedIn);
        }
        int newSize = linkedInService.findAll().size();
        assertEquals(size - 2, newSize);
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfUpdatedLinkedIns() {
        int size = linkedInService.findAll().size();
        List<LinkedIn> linkedInsToUpdate = new ArrayList<>(student1LinkedInList);
        int i = 0;
        for (LinkedIn linkedIn : linkedInsToUpdate) {
            i++;
            if (i > 2) {
                break;
            }
            LinkedIn newlinkedIn = linkedInRepo.findById(linkedIn.getId()).get();

            linkedInRepo.save(newlinkedIn);
        }
        assertEquals(size, linkedInService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllUpdateReallyHappened() {
        List<LinkedIn> linkedInsToUpdate = new ArrayList<>(student1LinkedInList);
        int i = 0;
        long studentId1 = 0;
        for (LinkedIn linkedIn : linkedInsToUpdate) {
        	studentId1 = linkedIn.getStudent().getId();
            i++;
            if (i > 2) {
                break;
            }
            LinkedIn newLinkedIn = linkedInRepo.findById(linkedIn.getId()).get();
            newLinkedIn.setBanner(false);
            linkedInRepo.save(newLinkedIn);
        }
        List<LinkedIn> everythingInDatabase = linkedInService.findAll();
        int j = 0;
        for (LinkedIn linkedIn : everythingInDatabase) {
            if (linkedIn.getStudent() != null && linkedIn.getStudent().getId() == studentId1 && linkedIn.getBanner()) {
                j++;
            }
        }
        assertEquals(2, j);
    }

    @Test
    @Transactional
    void testCreateLinkedInWithNullLinkedIn() {
        student1LinkedInList.forEach(linkedIn -> {
            LinkedIn linkedInUt = linkedInService.saveByUid(null, student1Uid);
            assertNull(linkedInUt);
        });
    }

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

    @Test
	@Transactional
	void testFindByLinkedInIdWhenLinkedInIdIsNull() {
        assertNull(linkedInService.findById(null));
	}

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
            linkedInService.delete(linkedIn);
            foundLinkedIn = linkedInRepo.findById(originalId).orElse(null);
            assertNotNull(foundLinkedIn);
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
