package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.GitHubRepository;
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
public class GitHubServiceTest {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentService studentService;

    @Autowired
    private GitHubService gitHubService;
    @Autowired
    private GitHubRepository gitHubRepo;

    Student student1;
    Student student2;

    StudentDTO studentDTO1;
    StudentDTO studentDTO2;

    String student1Uid;
    String student2Uid;

    List<CheckinDTO> student1CheckinDTOList;
    List<CheckinDTO> student2CheckinDTOList;

    List<StudentDTO> student1StudentDTOList;

    List<GitHub> student1GitHubList;

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

        student1GitHubList = new ArrayList<>();

        List<StudentDTO> student1StudentDTOList = new ArrayList<StudentDTO>();

        for (int i = 0; i < 4; i++) {
            GitHub gitHub  = new GitHub();
            gitHub.setUrl("Blocker" + i);
            gitHub.setHandle("Blocker" + i);
            gitHub.setEnhancedReadMe("Blocker" + i);
            gitHub.setRenamedAssignments("Blocker" + i);
            gitHub.setPinnedRepos("Blocker" + i);
            gitHub.setExternalLinks("Blocker" + i);
            gitHub.setImage("Blocker" + i);
            gitHub.setHeadline("Blocker" + i);
            gitHub.setImage("Blocker" + i);
            gitHub.setStudent(student1);
            gitHub.getStudent().setUid(student1Uid);
            gitHubRepo.save(gitHub);
            student1GitHubList.add(gitHub);
        }
    }
    @AfterEach
    void cleanUpData() {
        student1GitHubList.forEach(gitHub -> {
            gitHubRepo.findById(gitHub.getId()).ifPresent(gitHubRepo::delete);
        });
        studentRepo.delete(student1);
        studentRepo.delete(student2);
    }

    @Test
    @Transactional
    void testSetUpCreateGitHub() {
        assertEquals(4, student1GitHubList.size());
    }

    @Test
	@Transactional
	void testSaveGitHub() {
        AtomicLong i = new AtomicLong(0L);
		student1GitHubList.forEach(gitHubFromList -> {

            gitHubFromList.setHeadline("Test");
			GitHub savedGitHub = gitHubService.saveByUid(gitHubFromList, student1Uid);
            assertTrue(savedGitHub.getId() > i.get());
            i.set(savedGitHub.getId());
            assertEquals(gitHubFromList.getHeadline(), savedGitHub.getHeadline());
            assertEquals(gitHubFromList.getStudent().getId(), savedGitHub.getStudent().getId());
		});
	}


    @Test
    @Transactional
    void testSaveByUidForNullUID() {
        student1GitHubList.forEach(gitHub -> {
            GitHub gitHubUt = gitHubService.saveByUid(gitHub, null);
            assertNull(gitHubUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForValidUID() {
        String goodUid = student1Uid;
        student1GitHubList.forEach(gitHub -> {
            GitHub gitHubUt = gitHubService.saveByUid(gitHub, goodUid);
             assertNotNull(gitHubUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForInvalidUID() {
        String badUid = "abc";
        student1GitHubList.forEach(gitHub -> {
            GitHub gitHubUt = gitHubService.saveByUid(gitHub, badUid);
            assertNull(gitHubUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForUpdateWithWrongUID() {
        String wrongUid = student2Uid;
        student1GitHubList.forEach(gitHub -> {

            GitHub gitHubUt = gitHubService.saveByUid(gitHub, wrongUid);
            assertNull(gitHubUt);
        });
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfAddedGitHubs() {

        int start = student1GitHubList.size();
        int size = gitHubService.findAll().size();
        assertTrue(size >= start);
        for (int i = 0; i < 2; i++) {
            GitHub gitHub = new GitHub();
            gitHub.setStudent(student1);
            gitHub.setHeadline("headline");
            gitHubRepo.save(gitHub);
            GitHub newGitHub = gitHub;
            student1GitHubList.add(newGitHub);
        }
        assertEquals(size + 2, gitHubService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfRemovedGitHubs() {
        int size = gitHubService.findAll().size();
        List<GitHub> gitHubsToRemove = new ArrayList<>(student1GitHubList);
        int i = 0;
        for (GitHub gitHub : gitHubsToRemove) {
            i++;
            if (i > 2) {
                break;
            }
            GitHub newGitHub = gitHubRepo.findById(gitHub.getId()).get();
            gitHubRepo.delete(gitHub);
            student1GitHubList.remove(gitHub);
        }
        int newSize = gitHubService.findAll().size();
        assertEquals(size - 2, newSize);
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfUpdatedGitHubs() {
        int size = gitHubService.findAll().size();
        List<GitHub> gitHubsToUpdate = new ArrayList<>(student1GitHubList);
        int i = 0;
        for (GitHub gitHub : gitHubsToUpdate) {
            i++;
            if (i > 2) {
                break;
            }
            GitHub newgitHub = gitHubRepo.findById(gitHub.getId()).get();

            gitHubRepo.save(newgitHub);
        }
        assertEquals(size, gitHubService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllUpdateReallyHappened() {
        List<GitHub> gitHubsToUpdate = new ArrayList<>(student1GitHubList);
        int i = 0;
        String randomString = UUID.randomUUID().toString();
        long studentId1 = 0;
        for (GitHub gitHub : gitHubsToUpdate) {
        	studentId1 = gitHub.getStudent().getId();
            i++;
            if (i > 2) {
                break;
            }
            GitHub newGitHub = gitHubRepo.findById(gitHub.getId()).get();
            newGitHub.setHeadline(randomString);
            gitHubRepo.save(newGitHub);
        }
        List<GitHub> everythingInDatabase = gitHubService.findAll();
        int j = 0;
        for (GitHub gitHub : everythingInDatabase) {
            if (gitHub.getStudent() != null && gitHub.getStudent().getId() == studentId1 && gitHub.getHeadline().equals(randomString)) {
                j++;
            }
        }
        assertEquals(2, j);
    }

    @Test
    @Transactional
    void testCreateGitHubWithNullGitHub() {
        student1GitHubList.forEach(gitHub -> {
            GitHub gitHubUt = gitHubService.saveByUid(null, student1Uid);
            assertNull(gitHubUt);
        });
    }

    @Test
	@Transactional
	void testFindByGitHubId() {
		student1GitHubList.forEach(gitHub -> {
            assertEquals(gitHub, gitHubService.findById(gitHub.getId()));
        });
	}

    @Test
	@Transactional
	void testFindByGitHubIdWhenGitHubIdIsOutOfBounds() {
        assertThrows(NoSuchElementException.class, () -> {
            gitHubService.findById(Long.MAX_VALUE);
        });
	}

    @Test
	@Transactional
	void testFindByGitHubIdWhenGitHubIdIsNull() {
        assertNull(gitHubService.findById(null));
	}

    @Test
    @Transactional
    void testDeleteWhenGitHubIDIsInvalid() {
        Random random = new Random();
        Long wrongId = (long) (random.nextInt(1000) + 1);
        student1GitHubList.forEach(gitHub -> {
            Long originalId = gitHub.getId();
            GitHub foundGitHub = gitHubRepo.findById(originalId).orElse(null);
            assertNotNull(foundGitHub);
            gitHub.setId(wrongId);

            gitHubService.delete(gitHub);
            foundGitHub = gitHubRepo.findById(originalId).orElse(null);
            assertNotNull(foundGitHub);

            gitHub.setId(originalId);
        });
    }

    @Test
    @Transactional
    void testDeleteWhenGitHubIDIsValid() {
        student1GitHubList.forEach(gitHub -> {
            Long originalId = gitHub.getId();
            GitHub storedGitHub = gitHubRepo.findById(originalId).orElse(null);
            assertNotNull(storedGitHub);

            gitHubService.delete(gitHub);
            GitHub foundGitHub = gitHubRepo.findById(originalId).orElse(null);
            assertNull(foundGitHub);

            gitHub = storedGitHub;

        });
    }
    
    @Test
    @Transactional
    void testDeleteRecordsWithNoStudentAssociated() {
        GitHub gitHubWithoutStudent = new GitHub();
        gitHubWithoutStudent.setStudent(null);
        gitHubRepo.save(gitHubWithoutStudent);

        gitHubService.deleteRecordsWithNoStudentAssociated();

        List<GitHub> allGitHubs = gitHubService.findAll();
        int count = 0;
        for (GitHub gitHub : allGitHubs) {
            if (gitHub.getStudent() == null) {
                count++;
            }
        }
        assertEquals(0, count);
    }



}
