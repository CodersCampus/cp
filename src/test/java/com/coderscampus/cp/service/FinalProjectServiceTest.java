package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Finalproject;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.FinalprojectRepository;
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
public class FinalProjectServiceTest {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentService studentService;

    @Autowired
    private FinalprojectService finalprojectService;
    @Autowired
    private FinalprojectRepository finalprojectRepo;

    Student student1;
    Student student2;

    StudentDTO studentDTO1;
    StudentDTO studentDTO2;

    String student1Uid;
    String student2Uid;

    List<CheckinDTO> student1CheckinDTOList;
    List<CheckinDTO> student2CheckinDTOList;

    List<StudentDTO> student1StudentDTOList;

    List<Finalproject> student1FinalprojectList;

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

        student1FinalprojectList = new ArrayList<>();

        List<StudentDTO> student1StudentDTOList = new ArrayList<StudentDTO>();

        for (int i = 0; i < 4; i++) {
            Finalproject finalproject  = new Finalproject();

            finalproject.setUid(student1Uid);
            finalproject.setProjectName("Blocker" + i);
            finalproject.setProposal("Blocker" + i);
            finalproject.setCrud("Blocker" + i);
            finalproject.setTables("Blocker" + i);
            finalproject.setViews("Blocker" + i);
            finalproject.setStudent(student1);
            finalproject.getStudent().setUid(student1Uid);
            finalprojectRepo.save(finalproject);
            student1FinalprojectList.add(finalproject);
        }
    }
    @AfterEach
    void cleanUpData() {
        student1FinalprojectList.forEach(finalproject -> {
            finalprojectRepo.findById(finalproject.getId()).ifPresent(finalprojectRepo::delete);
        });
        studentRepo.delete(student1);
        studentRepo.delete(student2);
    }

    @Test
    @Transactional
    void testSetUpCreateFinalproject() {
        assertEquals(4, student1FinalprojectList.size());
    }

    @Test
	@Transactional
	void testSaveFinalproject() {
        AtomicLong i = new AtomicLong(0L);
		student1FinalprojectList.forEach(finalprojectFromList -> {

            finalprojectFromList.setProposal("Test");
			Finalproject savedFinalproject = finalprojectService.save(finalprojectFromList);
            assertTrue(savedFinalproject.getId() > i.get());
            i.set(savedFinalproject.getId());
            assertEquals(finalprojectFromList.getProposal(), savedFinalproject.getProposal());
            assertEquals(finalprojectFromList.getStudent().getId(), savedFinalproject.getStudent().getId());
		});
	}


    @Test
    @Transactional
    void testSaveByUidForNullUID() {
        student1FinalprojectList.forEach(finalproject -> {
            Finalproject finalprojectUt = finalprojectService.saveByUid(finalproject, null);
            assertNull(finalprojectUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForValidUID() {
        String goodUid = student1Uid;
        student1FinalprojectList.forEach(finalproject -> {
            Finalproject finalprojectUt = finalprojectService.saveByUid(finalproject, goodUid);
             assertNotNull(finalprojectUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForInvalidUID() {
        String badUid = "abc";
        student1FinalprojectList.forEach(finalproject -> {
            Finalproject finalprojectUt = finalprojectService.saveByUid(finalproject, badUid);
            assertNull(finalprojectUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForUpdateWithWrongUID() {
        String wrongUid = student2Uid;
        student1FinalprojectList.forEach(finalproject -> {

            Finalproject finalprojectUt = finalprojectService.saveByUid(finalproject, wrongUid);
            assertNull(finalprojectUt);
        });
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfAddedFinalprojects() {

        int start = student1FinalprojectList.size();
        int size = finalprojectService.findAll().size();
        assertTrue(size >= start);
        for (int i = 0; i < 2; i++) {
            Finalproject finalproject = new Finalproject();
            finalproject.setStudent(student1);
            finalproject.setProposal("proposal");
            finalprojectRepo.save(finalproject);
            Finalproject newFinalproject = finalproject;
            student1FinalprojectList.add(newFinalproject);
        }
        assertEquals(size + 2, finalprojectService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfRemovedFinalprojects() {
        int size = finalprojectService.findAll().size();
        List<Finalproject> finalprojectsToRemove = new ArrayList<>(student1FinalprojectList);
        int i = 0;
        for (Finalproject finalproject : finalprojectsToRemove) {
            i++;
            if (i > 2) {
                break;
            }
            Finalproject newFinalproject = finalprojectRepo.findById(finalproject.getId()).get();
            finalprojectRepo.delete(finalproject);
            student1FinalprojectList.remove(finalproject);
        }
        int newSize = finalprojectService.findAll().size();
        assertEquals(size - 2, newSize);
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfUpdatedFinalprojects() {
        int size = finalprojectService.findAll().size();
        List<Finalproject> finalprojectsToUpdate = new ArrayList<>(student1FinalprojectList);
        int i = 0;
        for (Finalproject finalproject : finalprojectsToUpdate) {
            i++;
            if (i > 2) {
                break;
            }
            Finalproject newfinalproject = finalprojectRepo.findById(finalproject.getId()).get();

            finalprojectRepo.save(newfinalproject);
        }
        assertEquals(size, finalprojectService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllUpdateReallyHappened() {
        List<Finalproject> finalprojectsToUpdate = new ArrayList<>(student1FinalprojectList);
        int i = 0;
        String randomString = UUID.randomUUID().toString();
        long studentId1 = 0;
        for (Finalproject finalproject : finalprojectsToUpdate) {
        	studentId1 = finalproject.getStudent().getId();
            i++;
            if (i > 2) {
                break;
            }
            Finalproject newFinalproject = finalprojectRepo.findById(finalproject.getId()).get();
            newFinalproject.setProposal(randomString);
            finalprojectRepo.save(newFinalproject);
        }
        List<Finalproject> everythingInDatabase = finalprojectService.findAll();
        int j = 0;
        for (Finalproject finalproject : everythingInDatabase) {
            if (finalproject.getStudent().getId() == studentId1 && finalproject.getProposal().equals(randomString)) {
                j++;
            }
        }
        assertEquals(2, j);
    }

    @Test
    @Transactional
    void testCreateFinalprojectWithNullFinalproject() {
        student1FinalprojectList.forEach(finalproject -> {
            Finalproject finalprojectUt = finalprojectService.saveByUid(null, student1Uid);
            assertNull(finalprojectUt);
        });
    }

    @Test
	@Transactional
	void testFindByFinalprojectId() {
		student1FinalprojectList.forEach(finalproject -> {
            assertEquals(finalproject, finalprojectService.findById(finalproject.getId()));
        });
	}

    @Test
	@Transactional
	void testFindByFinalprojectIdWhenFinalprojectIdIsOutOfBounds() {
        assertThrows(NoSuchElementException.class, () -> {
            finalprojectService.findById(Long.MAX_VALUE);
        });
	}

    @Test
	@Transactional
	void testFindByFinalprojectIdWhenFinalprojectIdIsNull() {
        assertNull(finalprojectService.findById(null));
	}

    @Test
    @Transactional
    void testDeleteWhenFinalprojectIDIsInvalid() {
        Random random = new Random();
        Long wrongId = (long) (random.nextInt(1000) + 1);
        student1FinalprojectList.forEach(finalproject -> {
            Long originalId = finalproject.getId();
            Finalproject foundFinalproject = finalprojectRepo.findById(originalId).orElse(null);
            assertNotNull(foundFinalproject);
            finalproject.setId(wrongId);
            finalprojectService.delete(finalproject);
            foundFinalproject = finalprojectRepo.findById(originalId).orElse(null);
            assertNotNull(foundFinalproject);
            finalproject.setId(originalId);
        });
    }

    @Test
    @Transactional
    void testDeleteWhenFinalprojectIDIsValid() {
        student1FinalprojectList.forEach(finalproject -> {
            Long originalId = finalproject.getId();
            Finalproject storedFinalproject = finalprojectRepo.findById(originalId).orElse(null);
            assertNotNull(storedFinalproject);

            finalprojectService.delete(finalproject);
            Finalproject foundFinalproject = finalprojectRepo.findById(originalId).orElse(null);
            assertNull(foundFinalproject);

            finalproject = storedFinalproject;

        });
    }



}
