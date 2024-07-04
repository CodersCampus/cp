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

    for(int i = 0; i < 4; i++){
        CheckinDTO checkinDTO = new CheckinDTO();
        checkinDTO.setBlockerDescription("Blokker" +i);
     //   checkinDTO.setStudentId();
    }
    //    Consider for later, more streamlined
    @BeforeEach
    void prepData() {

        studentDTO1 = new StudentDTO();
        studentDTO2 = new StudentDTO();

        student1Uid = UUID.randomUUID().toString();
        student2Uid = UUID.randomUUID().toString();

        student1 = new Student(student1Uid, "name1", 1, "IntelliJ", false, "mentor1", null);
        student2 = new Student(student2Uid, "name2", 2, "IntelliJ", false, "mentor2", null);

        student1CheckinDTOList = new ArrayList<>();
        student2CheckinDTOList = new ArrayList<>();
    }
    @AfterEach
    void cleanUpData(){
        checkinRepo.deleteAll();
        studentRepo.deleteAll();
    }

    @Test
    @Transactional
    void testNullUID() {
//  create a new checkingDTO
//  save the checkingDTO using the saveBYUid method and a nullUID
// assertEquals = nullDTO
    }

    @Test
    @Transactional
    void testValidUID() {

    }

    @Test
    @Transactional
    void testUpdateWithExistingUID() {

    }

    @Test
    @Transactional
    void testUpdateWithWrongUID() {

    }




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
    void testSaveByUid() {
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
        // Confirm existence of second checkin in database
//        assertTrue(checkinRepo.findById(foundCheckin.getId()).isPresent());
        // Change all relevant (non-id, uid, and creation date) fields in checkin
        checkinDTO.setBlockers(false);
        checkinDTO.setRole(Checkin.Role.OBSERVER);
        checkinDTO.setBlockerDescription("Blep");
        checkinDTO.setCodingType(Checkin.CodingType.CODE_REVIEW);
        checkinDTO.setNextAssignment(11);
        // Save by uid against the change
        checkinService.saveByUid(checkinDTO, uid);
        // Get the changed record
        List<CheckinDTO> checkinList = checkinService.findByUid(uid);
        CheckinDTO changedCheckin = null;
        for (CheckinDTO checkin1 : checkinList) {
            if (checkinDTO.getId().equals(checkin1.getId())) {
                changedCheckin = checkin1;
            }
        }
        // verify that all fields changed
        assertEquals(false, changedCheckin.getBlockers());
        assertEquals("Blep", changedCheckin.getBlockerDescription());
        assertEquals(11, changedCheckin.getNextAssignment());
        assertEquals(Checkin.Role.OBSERVER, changedCheckin.getRole());
        assertEquals(Checkin.CodingType.CODE_REVIEW, changedCheckin.getCodingType());
        // Delete second checkin

        checkinService.delete(checkinDTO, uid);
        // Confirm deletion from database
        assertFalse(checkinRepo.findById(changedCheckin.getId()).isPresent());
        // Clean up by deleting student from database
        studentRepo.delete(student);
    }

    @Test
    @Transactional
    void testFindByUid() {
        // Create UID
        String uid = UUID.randomUUID().toString();
        // Create new student with new UID
        Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
        // Save the student
        studentRepo.save(student);
//        studentRepo.save(student2);
        // Create new checkin
        Checkin checkin = new Checkin(uid, null, 9, true, "assignment9", student, Checkin.Role.CODER,
                Checkin.CodingType.CRUD);
        Checkin checkin2 = new Checkin(uid, null, 11, true, "assignment10", student, Checkin.Role.CODER,
                Checkin.CodingType.CRUD);
        // Save checkin
        CheckinDTO checkinDTO1 = checkinService.saveByUid(new CheckinDTO(checkin), uid);
        CheckinDTO checkinDTO2 = checkinService.saveByUid(new CheckinDTO(checkin2), uid);
//
        List<CheckinDTO> checkinDTOs = checkinService.findByUid(uid);
        // since using checkinService.findByUid in prev tests, index is not starting at 0
        // could use streams or arrraylist to have better looking code but it gets job done
        String ids = "";
        for (CheckinDTO checkinDTO : checkinDTOs) {
            ids = ids + checkinDTO.getId();
        }
        assertTrue(ids.contains("" + checkinDTO1.getId()));
        assertEquals(2, checkinDTOs.size());

        String assignment = "";
        for (CheckinDTO checkinDTO : checkinDTOs) {
            assignment = assignment + checkinDTO.getNextAssignment();
        }
        assertTrue(assignment.contains("" + checkinDTO1.getNextAssignment()));

        String assignment2 = "";
        for (CheckinDTO checkinDTO : checkinDTOs) {
            assignment2 = assignment2 + checkinDTO.getNextAssignment();
        }
        assertTrue(assignment.contains("" + checkinDTO2.getNextAssignment()));
    }

    @Test
    @Transactional
//    This will fail if "spring.jpa.hibernate.ddl-auto=create-drop" is changed in application.properties
    void testFindAll(){
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
	


