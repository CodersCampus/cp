package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Resume;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.ResumeRepository;
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
public class ResumeServiceTest {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentService studentService;

    @Autowired
    private ResumeService resumeService;
    @Autowired
    private ResumeRepository resumeRepo;

    Student student1;
    Student student2;

    StudentDTO studentDTO1;
    StudentDTO studentDTO2;

    String student1Uid;
    String student2Uid;

    List<CheckinDTO> student1CheckinDTOList;
    List<CheckinDTO> student2CheckinDTOList;

    List<StudentDTO> student1StudentDTOList;

    List<Resume> student1ResumeList;

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

        student1ResumeList = new ArrayList<>();

        List<StudentDTO> student1StudentDTOList = new ArrayList<StudentDTO>();

        for (int i = 0; i < 4; i++) {
            Resume resume  = new Resume();

            resume.setPhoneNumber("Blocker" + i);
            resume.setEmail("Blocker" + i);
            resume.setCity("Blocker" + i);
            resume.setState("Blocker" + i);
            resume.setLinkedIn("Blocker" + i);
            resume.setGitHub("Blocker" + i);
            resume.setSummary("Blocker" + i);
            resume.setSkills("Blocker" + i);
            resume.setWorkExperience("Blocker" + i);
            resume.setEducation("Blocker" + i);
            resume.setProjects("Blocker" + i);
            resume.setStudent(student1);
            resume.getStudent().setUid(student1Uid);
            resumeRepo.save(resume);
            student1ResumeList.add(resume);
        }
    }
    @AfterEach
    void cleanUpData() {
        student1ResumeList.forEach(resume -> {
            resumeRepo.findById(resume.getId()).ifPresent(resumeRepo::delete);
        });
        studentRepo.delete(student1);
        studentRepo.delete(student2);
    }

    @Test
    @Transactional
    void testSetUpCreateResume() {
        assertEquals(4, student1ResumeList.size());
    }

    @Test
	@Transactional
	void testSaveResume() {
        AtomicLong i = new AtomicLong(0L);
		student1ResumeList.forEach(resumeFromList -> {

            resumeFromList.setSkills("Test");
			Resume savedResume = resumeService.save(resumeFromList);
            assertTrue(savedResume.getId() > i.get());
            i.set(savedResume.getId());
            assertEquals(resumeFromList.getSkills(), savedResume.getSkills());
            assertEquals(resumeFromList.getStudent().getId(), savedResume.getStudent().getId());
		});
	}


    @Test
    @Transactional
    void testSaveByUidForNullUID() {
        student1ResumeList.forEach(resume -> {
            Resume resumeUt = resumeService.saveByUid(resume, null);
            assertNull(resumeUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForValidUID() {
        String goodUid = student1Uid;
        student1ResumeList.forEach(resume -> {
            Resume resumeUt = resumeService.saveByUid(resume, goodUid);
             assertNotNull(resumeUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForInvalidUID() {
        String badUid = "abc";
        student1ResumeList.forEach(resume -> {
            Resume resumeUt = resumeService.saveByUid(resume, badUid);
            assertNull(resumeUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForUpdateWithWrongUID() {
        String wrongUid = student2Uid;
        student1ResumeList.forEach(resume -> {

            Resume resumeUt = resumeService.saveByUid(resume, wrongUid);
            assertNull(resumeUt);
        });
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfAddedResumes() {

        int start = student1ResumeList.size();
        int size = resumeService.findAll().size();
        assertTrue(size >= start);
        for (int i = 0; i < 2; i++) {
            Resume resume = new Resume();
            resume.setStudent(student1);
            resume.setSkills("skills");
            resumeRepo.save(resume);
            Resume newResume = resume;
            student1ResumeList.add(newResume);
        }
        assertEquals(size + 2, resumeService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfRemovedResumes() {
        int size = resumeService.findAll().size();
        List<Resume> resumesToRemove = new ArrayList<>(student1ResumeList);
        int i = 0;
        for (Resume resume : resumesToRemove) {
            i++;
            if (i > 2) {
                break;
            }
            Resume newResume = resumeRepo.findById(resume.getId()).get();
            resumeRepo.delete(resume);
            student1ResumeList.remove(resume);
        }
        int newSize = resumeService.findAll().size();
        assertEquals(size - 2, newSize);
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfUpdatedResumes() {
        int size = resumeService.findAll().size();
        List<Resume> resumesToUpdate = new ArrayList<>(student1ResumeList);
        int i = 0;
        for (Resume resume : resumesToUpdate) {
            i++;
            if (i > 2) {
                break;
            }
            Resume newresume = resumeRepo.findById(resume.getId()).get();

            resumeRepo.save(newresume);
        }
        assertEquals(size, resumeService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllUpdateReallyHappened() {
        List<Resume> resumesToUpdate = new ArrayList<>(student1ResumeList);
        int i = 0;
        String randomString = UUID.randomUUID().toString();
        long studentId1 = 0;
        for (Resume resume : resumesToUpdate) {
        	studentId1 = resume.getStudent().getId();
            i++;
            if (i > 2) {
                break;
            }
            Resume newResume = resumeRepo.findById(resume.getId()).get();
            newResume.setSkills(randomString);
            resumeRepo.save(newResume);
        }
        List<Resume> everythingInDatabase = resumeService.findAll();
        int j = 0;
        for (Resume resume : everythingInDatabase) {
            if (resume.getStudent().getId() == studentId1 && resume.getSkills().equals(randomString)) {
                j++;
            }
        }
        assertEquals(2, j);
    }

    @Test
    @Transactional
    void testCreateResumeWithNullResume() {
        student1ResumeList.forEach(resume -> {
            Resume resumeUt = resumeService.saveByUid(null, student1Uid);
            assertNull(resumeUt);
        });
    }

    @Test
	@Transactional
	void testFindByResumeId() {
		student1ResumeList.forEach(resume -> {
            assertEquals(resume, resumeService.findById(resume.getId()));
        });
	}

    @Test
	@Transactional
	void testFindByResumeIdWhenResumeIdIsOutOfBounds() {
        assertThrows(NoSuchElementException.class, () -> {
            resumeService.findById(Long.MAX_VALUE);
        });
	}

    @Test
	@Transactional
	void testFindByResumeIdWhenResumeIdIsNull() {
        assertNull(resumeService.findById(null));
	}

    @Test
    @Transactional
    void testDeleteWhenResumeIDIsInvalid() {
        Random random = new Random();
        Long wrongId = (long) (random.nextInt(1000) + 1);
        student1ResumeList.forEach(resume -> {
            Long originalId = resume.getId();
            Resume foundResume = resumeRepo.findById(originalId).orElse(null);
            assertNotNull(foundResume);
            resume.setId(wrongId);
            resumeService.delete(resume);
            foundResume = resumeRepo.findById(originalId).orElse(null);
            assertNotNull(foundResume);
            resume.setId(originalId);
        });
    }

    @Test
    @Transactional
    void testDeleteWhenResumeIDIsValid() {
        student1ResumeList.forEach(resume -> {
            Long originalId = resume.getId();
            Resume storedResume = resumeRepo.findById(originalId).orElse(null);
            assertNotNull(storedResume);

            resumeService.delete(resume);
            Resume foundResume = resumeRepo.findById(originalId).orElse(null);
            assertNull(foundResume);

            resume = storedResume;

        });
    }



}

