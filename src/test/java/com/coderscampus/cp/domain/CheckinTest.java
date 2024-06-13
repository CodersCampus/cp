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
    void testCheckinController() {
        String uid = UUID.randomUUID().toString();
        Student existingStudent = new Student(uid, "Bobby", 17, "IntelliJ", false, "name", null); // Creates hypothetical existing student
        existingStudent = studentRepo.save(existingStudent); // Saves that existing student to the database
        Checkin checkin = new Checkin(uid, null, 9, true, "assignment9", existingStudent, Checkin.Role.CODER,
                Checkin.CodingType.CRUD);
        CheckinDTO checkinDTO1 = checkinService.saveByUid(new CheckinDTO(checkin), uid);
        assertNotNull(checkin.getStudent());
    }
}