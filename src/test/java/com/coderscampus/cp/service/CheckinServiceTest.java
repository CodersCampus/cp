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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
            checkinRepo.findById(checkinDTO.getId()).ifPresent(checkinRepo::delete);
        });
        student2CheckinDTOList.forEach(checkinDTO -> {
            checkinRepo.findById(checkinDTO.getId()).ifPresent(checkinRepo::delete);
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
    void testSaveByUidForNullUID() {
        student1CheckinDTOList.forEach(checkinDTO -> {
            CheckinDTO checkinDTOUt = checkinService.saveByUid(checkinDTO, null);
            assertNull(checkinDTOUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForValidUID() {
        String goodUid = student1Uid;
        student1CheckinDTOList.forEach(checkinDTO -> {
            CheckinDTO checkinDTOUt = checkinService.saveByUid(checkinDTO, goodUid);
            assertNotNull(checkinDTOUt);
        });
    }

    @Test
    @Transactional
    void testSaveByUidForInvalidUID() {
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
    void testSaveByUidForUpdateWithExistingUID() {
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
    void testSaveByUidForUpdateWithWrongUID() {
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

    @Test
    @Transactional
    void testFindAllCorrectCountOfAddedCheckins() {

        int start = student1CheckinDTOList.size();
        int size = checkinService.findAll().size();
        assertTrue(size >= start);
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
        assertEquals(size + 2, checkinService.findAll().size());
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfRemovedCheckins() {
        int size = checkinService.findAll().size();
        List<CheckinDTO> checkinDTOsToRemove = new ArrayList<>(student1CheckinDTOList);
        int i = 0;
        for (CheckinDTO checkinDTO : checkinDTOsToRemove) {
            i++;
            if (i > 2) {
                break;
            }
            Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
            checkinRepo.delete(checkin);
            student1CheckinDTOList.remove(checkinDTO);
        }
        int newSize = checkinService.findAll().size();
        assertEquals(size - 2, newSize);
    }

    @Test
    @Transactional
    void testFindAllCorrectCountOfUpdatedCheckins() {
        int size = checkinService.findAll().size();
        List<CheckinDTO> checkinDTOsToUpdate = new ArrayList<>(student1CheckinDTOList);
        int i = 0;
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
        List<CheckinDTO> checkinDTOsToUpdate = new ArrayList<>(student1CheckinDTOList);
        int i = 0;
        String randomString = UUID.randomUUID().toString();
        for (CheckinDTO checkinDTO : checkinDTOsToUpdate) {
            i++;
            if (i > 2) {
                break;
            }
            Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
            checkin.setBlockerDescription(randomString);
            checkinRepo.save(checkin);
        }
        List<CheckinDTO> everythingInDatabase = checkinService.findAll();
        int j = 0;
        for (CheckinDTO checkinDTO : everythingInDatabase) {
            if (checkinDTO.getBlockerDescription().equals(randomString)) {
                j++;
            }
        }
        assertEquals(2, j);
    }

    @Test
    @Transactional
    void testFindAllCheckinsAreReturnedInDescendingOrderByDate() {
        List<CheckinDTO> everythingInDatabase = checkinService.findAll();
        Instant previousDate = Instant.now();
        boolean allGood = true;
        for (CheckinDTO checkinDTO : everythingInDatabase) {
            if (previousDate.isBefore(checkinDTO.getDate())) {
                allGood = false;
                break;
            }
            previousDate = checkinDTO.getDate();
        }
        assertTrue(allGood);
    }

    @Test
    @Transactional
    void testCreateCheckinCheckinDTONull() {
        student1CheckinDTOList.forEach(checkinDTO -> {
            CheckinDTO checkinDTOUt = checkinService.saveByUid(null, student1Uid);
            assertNull(checkinDTOUt);
        });
    }

    @Test
    @Transactional
    void testCreateCheckinStudentIdFromUidDoesNotMatchStudentIdFromCheckinDto() {
        CheckinDTO checkinDTO = new CheckinDTO(student1.getId());
        checkinDTO.setNextAssignment(4);
        checkinDTO.setBlockers(false);
        assertNull(checkinService.saveByUid(checkinDTO, student2Uid));
    }

    @Test
    @Transactional
    void testDeleteWhenUidIsNull() {
        student1CheckinDTOList.forEach(checkinDTO -> {
            Checkin foundCheckin = checkinRepo.findById(checkinDTO.getId()).orElse(null);
            assertNotNull(foundCheckin);
            Long deleted = checkinService.delete(checkinDTO, null);
            foundCheckin = checkinRepo.findById(checkinDTO.getId()).orElse(null);
            assertNotNull(foundCheckin);
            assertEquals(0L, deleted);
        });
    }

    @Test
    @Transactional
    void testDeleteWhenCheckinDTOIsNull() {
        student1CheckinDTOList.forEach(checkinDTO -> {
            Checkin foundCheckin = checkinRepo.findById(checkinDTO.getId()).orElse(null);
            assertNotNull(foundCheckin);
            Long deleted = checkinService.delete(null, student1Uid);
            foundCheckin = checkinRepo.findById(checkinDTO.getId()).orElse(null);
            assertNotNull(foundCheckin);
            assertEquals(0L, deleted);
        });
    }

    @Test
    @Transactional
    void testDeleteWhenCheckinIDIsInvalid() {
        Random random = new Random();
        Long wrongId = (long) (random.nextInt(1000) + 1);
        student1CheckinDTOList.forEach(checkinDTO -> {
            Long originalId = checkinDTO.getId();
            Checkin foundCheckin = checkinRepo.findById(originalId).orElse(null);
            assertNotNull(foundCheckin);
            checkinDTO.setId(wrongId);
            Long deleted = checkinService.delete(checkinDTO, student1Uid);
            foundCheckin = checkinRepo.findById(originalId).orElse(null);
            assertNotNull(foundCheckin);
            assertEquals(0L, deleted);
            checkinDTO.setId(originalId);
        });
    }

    @Test
    @Transactional
    void testDeleteWhenUidIsValidAndMatchesOwner() {
        student1CheckinDTOList.forEach(checkinDTO -> {
            Long originalId = checkinDTO.getId();
            Checkin foundCheckin = checkinRepo.findById(originalId).orElse(null);
            assertNotNull(foundCheckin);
            Long deleted = checkinService.delete(checkinDTO, student1Uid);
            foundCheckin = checkinRepo.findById(originalId).orElse(null);
            assertNull(foundCheckin);
            assertEquals( checkinDTO.getId(), deleted);

        });
    }

    @Test
    @Transactional
    void testDeleteWhenUidDoesNotOwnCheckin() {
        student1CheckinDTOList.forEach(checkinDTO -> {
            Long originalId = checkinDTO.getId();
            Checkin foundCheckin = checkinRepo.findById(originalId).orElse(null);
            assertNotNull(foundCheckin);
            Long deleted = checkinService.delete(checkinDTO, student2Uid);
            foundCheckin = checkinRepo.findById(originalId).orElse(null);
            assertNotNull(foundCheckin);
            assertEquals(0L, deleted);
        });
    }


    @Test
    @Transactional
    void testFindByIdWhenIdAndUidMatchOwner() {
        //start with lines 383 - 386 in the above method
    }

    @Test
    @Transactional
    void testFindByIdWhenUidDoesNotMatchOwner() {

    }

    @Test
    @Transactional
    void testFindByIdWhenIdDoesNotExistInDatabase() {

    }

    @Test
    @Transactional
    void testFindByIdWhenIdIsNull() {

    }
    // Everything below this is abandoned for now to be replaced



//leaving this in because it doesn't seem logical because no one is calling checkInService.findById()
//    @Test
//    @Transactional
//    void testFindById() {
//        // Create UID
//        String uid = UUID.randomUUID().toString();
//        // Create new student with new UID
//        Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
//        // Save the student
//        studentRepo.save(student);
//        assertNotNull(student.getId());
//        // Create new checkin
//        Checkin checkin = new Checkin(uid, null, 9, true, "assignment9", student, Checkin.Role.CODER,
//                Checkin.CodingType.CRUD);
//        Checkin checkin2 = new Checkin(uid, null, 11, true, "assignment10", student, Checkin.Role.CODER,
//                Checkin.CodingType.CRUD);
//        // Save checkin
//        CheckinDTO checkinDTO1 = checkinService.saveByUid(new CheckinDTO(checkin), uid);
//        CheckinDTO checkinDTO2 = checkinService.saveByUid(new CheckinDTO(checkin2), uid);
//        // Check find by id
//        CheckinDTO foundCheckinDTO = checkinService.findById(checkinDTO1.getId(), uid);
//        assertEquals("assignment9", foundCheckinDTO.getBlockerDescription());
//        CheckinDTO foundCheckinDTO2 = checkinService.findById(checkinDTO2.getId(), uid);
//        assertEquals("assignment10", foundCheckinDTO2.getBlockerDescription());
//    }
}
