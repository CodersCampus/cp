package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Networkingresource;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.NetworkingresourceRepository;
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
public class NetworkingResourceServiceTest {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentService studentService;

    @Autowired
    private NetworkingresourceService networkingresourceService;
    @Autowired
    private NetworkingresourceRepository networkingresourceRepo;

    Student student1;
    Student student2;

    StudentDTO studentDTO1;
    StudentDTO studentDTO2;

    String student1Uid;
    String student2Uid;

    List<CheckinDTO> student1CheckinDTOList;
    List<CheckinDTO> student2CheckinDTOList;

    List<StudentDTO> student1StudentDTOList;

    List<Networkingresource> student1NetworkingresourceList;

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

        student1NetworkingresourceList = new ArrayList<>();

        List<StudentDTO> student1StudentDTOList = new ArrayList<StudentDTO>();

        for (int i = 0; i < 4; i++) {
            Networkingresource networkingresource  = new Networkingresource();

            networkingresource.setUid(student1Uid);
            networkingresource.setResourceName("Blocker" + i);
            networkingresource.setStudent(student1);
            networkingresource.getStudent().setUid(student1Uid);
            networkingresourceRepo.save(networkingresource);
            student1NetworkingresourceList.add(networkingresource);
        }
    }
    @AfterEach
    void cleanUpData() {
        student1NetworkingresourceList.forEach(networkingresource -> {
            networkingresourceRepo.findById(networkingresource.getId()).ifPresent(networkingresourceRepo::delete);
        });
        studentRepo.delete(student1);
        studentRepo.delete(student2);
    }

    @Test
    @Transactional
    void testSetUpCreateNetworkingresource() {
        assertEquals(4, student1NetworkingresourceList.size());
    }

    @Test
	@Transactional
	void testSaveNetworkingresource() {
        AtomicLong i = new AtomicLong(0L);
		student1NetworkingresourceList.forEach(networkingresourceFromList -> {

            networkingresourceFromList.setResourceName("Test");
			Networkingresource savedNetworkingresource = networkingresourceService.saveByUid(networkingresourceFromList, student1Uid);
            assertTrue(savedNetworkingresource.getId() > i.get());
            i.set(savedNetworkingresource.getId());
            assertEquals(networkingresourceFromList.getResourceName(), savedNetworkingresource.getResourceName());
            assertEquals(networkingresourceFromList.getStudent().getId(), savedNetworkingresource.getStudent().getId());
		});
	}


    @Test
    @Transactional
    void testSaveByUidForNullUID() {
        student1NetworkingresourceList.forEach(networkingresource -> {
            Networkingresource networkingresourceUt = networkingresourceService.saveByUid(networkingresource, null);
            assertNull(networkingresourceUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForValidUID() {
        String goodUid = student1Uid;
        student1NetworkingresourceList.forEach(networkingresource -> {
            Networkingresource networkingresourceUt = networkingresourceService.saveByUid(networkingresource, goodUid);
             assertNotNull(networkingresourceUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForInvalidUID() {
        String badUid = "abc";
        student1NetworkingresourceList.forEach(networkingresource -> {
            Networkingresource networkingresourceUt = networkingresourceService.saveByUid(networkingresource, badUid);
            assertNull(networkingresourceUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForUpdateWithWrongUID() {
        String wrongUid = student2Uid;
        student1NetworkingresourceList.forEach(networkingresource -> {

            Networkingresource networkingresourceUt = networkingresourceService.saveByUid(networkingresource, wrongUid);
            assertNull(networkingresourceUt);
        });
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfAddedNetworkingresources() {

        int start = student1NetworkingresourceList.size();
        int size = networkingresourceService.findAll().size();
        assertTrue(size >= start);
        for (int i = 0; i < 2; i++) {
            Networkingresource networkingresource = new Networkingresource();
            networkingresource.setStudent(student1);
            networkingresource.setResourceName("resourceName");
            networkingresourceRepo.save(networkingresource);
            Networkingresource newNetworkingresource = networkingresource;
            student1NetworkingresourceList.add(newNetworkingresource);
        }
        assertEquals(size + 2, networkingresourceService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfRemovedNetworkingresources() {
        int size = networkingresourceService.findAll().size();
        List<Networkingresource> networkingresourcesToRemove = new ArrayList<>(student1NetworkingresourceList);
        int i = 0;
        for (Networkingresource networkingresource : networkingresourcesToRemove) {
            i++;
            if (i > 2) {
                break;
            }
            Networkingresource newNetworkingresource = networkingresourceRepo.findById(networkingresource.getId()).get();
            networkingresourceRepo.delete(networkingresource);
            student1NetworkingresourceList.remove(networkingresource);
        }
        int newSize = networkingresourceService.findAll().size();
        assertEquals(size - 2, newSize);
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfUpdatedNetworkingresources() {
        int size = networkingresourceService.findAll().size();
        List<Networkingresource> networkingresourcesToUpdate = new ArrayList<>(student1NetworkingresourceList);
        int i = 0;
        for (Networkingresource networkingresource : networkingresourcesToUpdate) {
            i++;
            if (i > 2) {
                break;
            }
            Networkingresource newnetworkingresource = networkingresourceRepo.findById(networkingresource.getId()).get();

            networkingresourceRepo.save(newnetworkingresource);
        }
        assertEquals(size, networkingresourceService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllUpdateReallyHappened() {
        List<Networkingresource> networkingresourcesToUpdate = new ArrayList<>(student1NetworkingresourceList);
        int i = 0;
        String randomString = UUID.randomUUID().toString();
        long studentId1 = 0;
        for (Networkingresource networkingresource : networkingresourcesToUpdate) {
        	studentId1 = networkingresource.getStudent().getId();
            i++;
            if (i > 2) {
                break;
            }
            Networkingresource newNetworkingresource = networkingresourceRepo.findById(networkingresource.getId()).get();
            newNetworkingresource.setResourceName(randomString);
            networkingresourceRepo.save(newNetworkingresource);
        }
        List<Networkingresource> everythingInDatabase = networkingresourceService.findAll();
        int j = 0;
        for (Networkingresource networkingresource : everythingInDatabase) {
            if (networkingresource.getStudent() != null && networkingresource.getStudent().getId() == studentId1 && networkingresource.getResourceName().equals(randomString)) {
                j++;
            }
        }
        assertEquals(2, j);
    }

    @Test
    @Transactional
    void testCreateNetworkingresourceWithNullNetworkingresource() {
        student1NetworkingresourceList.forEach(networkingresource -> {
            Networkingresource networkingresourceUt = networkingresourceService.saveByUid(null, student1Uid);
            assertNull(networkingresourceUt);
        });
    }

    @Test
	@Transactional
	void testFindByNetworkingresourceId() {
		student1NetworkingresourceList.forEach(networkingresource -> {
            assertEquals(networkingresource, networkingresourceService.findById(networkingresource.getId()));
        });
	}

    @Test
	@Transactional
	void testFindByNetworkingresourceIdWhenNetworkingresourceIdIsOutOfBounds() {
        assertThrows(NoSuchElementException.class, () -> {
            networkingresourceService.findById(Long.MAX_VALUE);
        });
	}

    @Test
	@Transactional
	void testFindByNetworkingresourceIdWhenNetworkingresourceIdIsNull() {
        assertNull(networkingresourceService.findById(null));
	}

    @Test
    @Transactional
    void testDeleteWhenNetworkingresourceIDIsInvalid() {
        Random random = new Random();
        Long wrongId = (long) (random.nextInt(1000) + 1);
        student1NetworkingresourceList.forEach(networkingresource -> {
            Long originalId = networkingresource.getId();
            Networkingresource foundNetworkingresource = networkingresourceRepo.findById(originalId).orElse(null);
            assertNotNull(foundNetworkingresource);
            networkingresource.setId(wrongId);
            networkingresourceService.delete(networkingresource);
            foundNetworkingresource = networkingresourceRepo.findById(originalId).orElse(null);
            assertNotNull(foundNetworkingresource);
            networkingresource.setId(originalId);
        });
    }

    @Test
    @Transactional
    void testDeleteWhenNetworkingresourceIDIsValid() {
        student1NetworkingresourceList.forEach(networkingresource -> {
            Long originalId = networkingresource.getId();
            Networkingresource storedNetworkingresource = networkingresourceRepo.findById(originalId).orElse(null);
            assertNotNull(storedNetworkingresource);

            networkingresourceService.delete(networkingresource);
            Networkingresource foundNetworkingresource = networkingresourceRepo.findById(originalId).orElse(null);
            assertNull(foundNetworkingresource);

            networkingresource = storedNetworkingresource;

        });
    }
    
    @Test
    @Transactional
    void testGetItemsByStudentWhenMultipleStudentsExistWithMultipleItems() {
        for (int i = 0; i < 4; i++) {
            Networkingresource networkingresource = new Networkingresource();
            networkingresource.setStudent(student2);
            networkingresource.setUid(student2Uid);
            networkingresourceRepo.save(networkingresource);
        }

        List foundNetworkingresourcesOfStudent1 = networkingresourceService.findListByUid(student1Uid);
        Integer numberOfEntries = foundNetworkingresourcesOfStudent1.size();
        assertEquals(4, numberOfEntries);
    }
}
