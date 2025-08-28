package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Networkingperson;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.NetworkingpersonRepository;
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
public class NetworkingPersonServiceTest {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentService studentService;

    @Autowired
    private NetworkingpersonService networkingpersonService;
    @Autowired
    private NetworkingpersonRepository networkingpersonRepo;

    Student student1;
    Student student2;

    StudentDTO studentDTO1;
    StudentDTO studentDTO2;

    String student1Uid;
    String student2Uid;

    List<CheckinDTO> student1CheckinDTOList;
    List<CheckinDTO> student2CheckinDTOList;

    List<StudentDTO> student1StudentDTOList;

    List<Networkingperson> student1NetworkingpersonList;

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

        student1NetworkingpersonList = new ArrayList<>();

        List<StudentDTO> student1StudentDTOList = new ArrayList<StudentDTO>();

        for (int i = 0; i < 4; i++) {
            Networkingperson networkingperson  = new Networkingperson();

            networkingperson.setUid(student1Uid);
            networkingperson.setTechStack("Blocker" + i);
            networkingperson.setFirstContactDate("Blocker" + i);
            networkingperson.setLastContactDate("Blocker" + i);
            networkingperson.setOtherNotesAboutPerson("Blocker" + i);
            networkingperson.setStudent(student1);
            networkingperson.getStudent().setUid(student1Uid);
            networkingpersonRepo.save(networkingperson);
            student1NetworkingpersonList.add(networkingperson);
        }
    }
    @AfterEach
    void cleanUpData() {
        student1NetworkingpersonList.forEach(networkingperson -> {
            networkingpersonRepo.findById(networkingperson.getId()).ifPresent(networkingpersonRepo::delete);
        });
        studentRepo.delete(student1);
        studentRepo.delete(student2);
    }

    @Test
    @Transactional
    void testSetUpCreateNetworkingperson() {
        assertEquals(4, student1NetworkingpersonList.size());
    }

    @Test
	@Transactional
	void testSaveNetworkingperson() {
        AtomicLong i = new AtomicLong(0L);
		student1NetworkingpersonList.forEach(networkingpersonFromList -> {

            networkingpersonFromList.setTechStack("Test");
			Networkingperson savedNetworkingperson = networkingpersonService.saveByUid(networkingpersonFromList, student1Uid);
            assertTrue(savedNetworkingperson.getId() > i.get());
            i.set(savedNetworkingperson.getId());
            assertEquals(networkingpersonFromList.getTechStack(), savedNetworkingperson.getTechStack());
            assertEquals(networkingpersonFromList.getStudent().getId(), savedNetworkingperson.getStudent().getId());
		});
	}


    @Test
    @Transactional
    void testSaveByUidForNullUID() {
        student1NetworkingpersonList.forEach(networkingperson -> {
            Networkingperson networkingpersonUt = networkingpersonService.saveByUid(networkingperson, null);
            assertNull(networkingpersonUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForValidUID() {
        String goodUid = student1Uid;
        student1NetworkingpersonList.forEach(networkingperson -> {
            Networkingperson networkingpersonUt = networkingpersonService.saveByUid(networkingperson, goodUid);
             assertNotNull(networkingpersonUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForInvalidUID() {
        String badUid = "abc";
        student1NetworkingpersonList.forEach(networkingperson -> {
            Networkingperson networkingpersonUt = networkingpersonService.saveByUid(networkingperson, badUid);
            assertNull(networkingpersonUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForUpdateWithWrongUID() {
        String wrongUid = student2Uid;
        student1NetworkingpersonList.forEach(networkingperson -> {

            Networkingperson networkingpersonUt = networkingpersonService.saveByUid(networkingperson, wrongUid);
            assertNull(networkingpersonUt);
        });
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfAddedNetworkingpersons() {

        int start = student1NetworkingpersonList.size();
        int size = networkingpersonService.findAll().size();
        assertTrue(size >= start);
        for (int i = 0; i < 2; i++) {
            Networkingperson networkingperson = new Networkingperson();
            networkingperson.setStudent(student1);
            networkingperson.setTechStack("techStack");
            networkingpersonRepo.save(networkingperson);
            Networkingperson newNetworkingperson = networkingperson;
            student1NetworkingpersonList.add(newNetworkingperson);
        }
        assertEquals(size + 2, networkingpersonService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfRemovedNetworkingpersons() {
        int size = networkingpersonService.findAll().size();
        List<Networkingperson> networkingpersonsToRemove = new ArrayList<>(student1NetworkingpersonList);
        int i = 0;
        for (Networkingperson networkingperson : networkingpersonsToRemove) {
            i++;
            if (i > 2) {
                break;
            }
            Networkingperson newNetworkingperson = networkingpersonRepo.findById(networkingperson.getId()).get();
            networkingpersonRepo.delete(networkingperson);
            student1NetworkingpersonList.remove(networkingperson);
        }
        int newSize = networkingpersonService.findAll().size();
        assertEquals(size - 2, newSize);
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfUpdatedNetworkingpersons() {
        int size = networkingpersonService.findAll().size();
        List<Networkingperson> networkingpersonsToUpdate = new ArrayList<>(student1NetworkingpersonList);
        int i = 0;
        for (Networkingperson networkingperson : networkingpersonsToUpdate) {
            i++;
            if (i > 2) {
                break;
            }
            Networkingperson newnetworkingperson = networkingpersonRepo.findById(networkingperson.getId()).get();

            networkingpersonRepo.save(newnetworkingperson);
        }
        assertEquals(size, networkingpersonService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllUpdateReallyHappened() {
        List<Networkingperson> networkingpersonsToUpdate = new ArrayList<>(student1NetworkingpersonList);
        int i = 0;
        String randomString = UUID.randomUUID().toString();
        long studentId1 = 0;
        for (Networkingperson networkingperson : networkingpersonsToUpdate) {
        	studentId1 = networkingperson.getStudent().getId();
            i++;
            if (i > 2) {
                break;
            }
            Networkingperson newNetworkingperson = networkingpersonRepo.findById(networkingperson.getId()).get();
            newNetworkingperson.setTechStack(randomString);
            networkingpersonRepo.save(newNetworkingperson);
        }
        List<Networkingperson> everythingInDatabase = networkingpersonService.findAll();
        int j = 0;
        for (Networkingperson networkingperson : everythingInDatabase) {
            if (networkingperson.getStudent() != null && networkingperson.getStudent().getId() == studentId1 && networkingperson.getTechStack().equals(randomString)) {
                j++;
            }
        }
        assertEquals(2, j);
    }

    @Test
    @Transactional
    void testCreateNetworkingpersonWithNullNetworkingperson() {
        student1NetworkingpersonList.forEach(networkingperson -> {
            Networkingperson networkingpersonUt = networkingpersonService.saveByUid(null, student1Uid);
            assertNull(networkingpersonUt);
        });
    }

    @Test
	@Transactional
	void testFindByNetworkingpersonId() {
		student1NetworkingpersonList.forEach(networkingperson -> {
            assertEquals(networkingperson, networkingpersonService.findById(networkingperson.getId()));
        });
	}

    @Test
	@Transactional
	void testFindByNetworkingpersonIdWhenNetworkingpersonIdIsOutOfBounds() {
       assertThrows(NoSuchElementException.class, () -> {
            networkingpersonService.findById(Long.MAX_VALUE);
        });
	}

    @Test
	@Transactional
	void testFindByNetworkingpersonIdWhenNetworkingpersonIdIsNull() {
        assertNull(networkingpersonService.findById(null));
	}

    @Test
    @Transactional
    void testDeleteWhenNetworkingpersonIDIsInvalid() {
        Random random = new Random();
        Long wrongId = (long) (random.nextInt(1000) + 1);
        student1NetworkingpersonList.forEach(networkingperson -> {
            Long originalId = networkingperson.getId();
            Networkingperson foundNetworkingperson = networkingpersonRepo.findById(originalId).orElse(null);
            assertNotNull(foundNetworkingperson);
            networkingperson.setId(wrongId);
            networkingpersonService.delete(networkingperson);
            foundNetworkingperson = networkingpersonRepo.findById(originalId).orElse(null);
            assertNotNull(foundNetworkingperson);
            networkingperson.setId(originalId);
        });
    }

    @Test
    @Transactional
    void testDeleteWhenNetworkingpersonIDIsValid() {
        student1NetworkingpersonList.forEach(networkingperson -> {
            Long originalId = networkingperson.getId();
            Networkingperson storedNetworkingperson = networkingpersonRepo.findById(originalId).orElse(null);
            assertNotNull(storedNetworkingperson);

            networkingpersonService.delete(networkingperson);
            Networkingperson foundNetworkingperson = networkingpersonRepo.findById(originalId).orElse(null);
            assertNull(foundNetworkingperson);

            networkingperson = storedNetworkingperson;

        });
    }

    @Test
    @Transactional
    void testGetItemsByStudentWhenMultipleStudentsExistWithMultipleItems() {
        for (int i = 0; i < 4; i++) {
            Networkingperson networkingperson = new Networkingperson();
            networkingperson.setStudent(student2);
            networkingperson.setUid(student2Uid);
            networkingpersonRepo.save(networkingperson);
        }

        List foundNetworkingpersonsOfStudent1 = networkingpersonService.findListByUid(student1Uid);
        Integer numberOfEntries = foundNetworkingpersonsOfStudent1.size();
        assertEquals(4, numberOfEntries);
    }
}
