package com.coderscampus.cp.repository;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.service.CheckinService;
import com.coderscampus.cp.service.StudentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CheckinRepositoryTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CheckinService checkinService;
    @Autowired
    private CheckinRepository checkinRepo;

    @Test
    @Transactional
    void testSave() {
        String uid = UUID.randomUUID().toString();
        Student student = new Student(uid, "Bobby", 12, "IntelliJ", false, "name", null);
        studentRepo.save(student);
        Checkin checkin = new Checkin(uid, null, 9, true, "assignment9", student, Checkin.Role.CODER, Checkin.CodingType.CRUD);
        checkinRepo.save(checkin);
        Checkin checkin2 = checkinRepo.findById(checkin.getId()).get();
        assertNotNull(checkin2);
    }

}
