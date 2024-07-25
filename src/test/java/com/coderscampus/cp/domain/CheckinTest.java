package com.coderscampus.cp.domain;

import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.repository.StudentRepository;
import com.coderscampus.cp.service.CheckinService;
import com.coderscampus.cp.service.StudentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CheckinTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private CheckinService checkinService;

    @Test
    @Transactional
    //this tests the constructor that takes 2 parameters, a checkinDTO and a UID
    //should fail if studentUID does not match UID handed in separate parameter
    void testCheckinDTO_UIDConstructor() {
        String uid = UUID.randomUUID().toString();
        Student existingStudent = new Student(uid, "Bobby", 17, "IntelliJ", false, "name", null); // Creates hypothetical existing student
        existingStudent = studentRepo.save(existingStudent); // Saves that existing student to the database
//        Checkin checkin = new Checkin(uid, null, 9, true, "assignment9", existingStudent, Checkin.Role.CODER,
//                Checkin.CodingType.CRUD);
        CheckinDTO checkinDTO1 = new CheckinDTO(existingStudent.getId());
        //setting the values
        checkinDTO1.setNextAssignment(9);
        checkinDTO1.setBlockers(true);
        checkinDTO1.setBlockerDescription("assginment 10");
        checkinDTO1.setRole(Checkin.Role.CODER);
        checkinDTO1.setCodingType(Checkin.CodingType.CODE_REVIEW);
        checkinDTO1.setStudentId(existingStudent.getId());
//        Checkin checkin = new Checkin(checkinDTO1, uid);
//        assertNotNull(checkin.getStudent());
    }
}