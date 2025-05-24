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

            gitHubFromList.setHandle("Test");
			GitHub savedGitHub = gitHubService.save(gitHubFromList);
            assertTrue(savedGitHub.getId() > i.get());
            i.set(savedGitHub.getId());
            assertEquals(gitHubFromList.getHandle(), savedGitHub.getHandle());
            assertEquals(gitHubFromList.getStudent().getId(), savedGitHub.getStudent().getId());
		});
	}


//    @Test
//    @Transactional
//    void testSaveByUidForNullUID() {
//        student1GitHubList.forEach(gitHub -> {
//            GitHub gitHubUt = gitHubService.saveByUid(gitHub, null);
//            assertNull(gitHubUt);
//        });
//    }

    @Test
    @Transactional
    void testSaveByUidForValidUID() {
        String goodUid = student1Uid;
        student1GitHubList.forEach(gitHub -> {
            GitHub gitHubUt = gitHubService.saveByUid(gitHub, goodUid);
             assertNotNull(gitHubUt);
        });
    }

//    @Test
//    @Transactional
//    void testSaveByUidForInvalidUID() {
//        String badUid = "abc";
//        student1GitHubList.forEach(gitHub -> {
//            GitHub gitHubUt = gitHubService.saveByUid(gitHub, badUid);
//            assertNull(gitHubUt);
//        });
//    }

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

//    @Test
//	@Transactional
//	void testFindByGitHubIdWhenGitHubIdIsNull() {
//        assertThrows(null, () -> {
//            gitHubService.findById(null);
//        });
//	}
    

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

            //Long deleted = gitHubService.delete(gitHub);
            //replaced because it returns void
            gitHubService.delete(gitHub);
            foundGitHub = gitHubRepo.findById(originalId).orElse(null);
            assertNotNull(foundGitHub);

            //assertEquals(0L, deleted);
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



}
