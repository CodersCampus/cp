package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class CheckinServiceTest {
    @Autowired
    private CheckinService checkinService;
    @Autowired
    private CheckinRepository checkinRepo;

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepo;

    @BeforeEach
    void setup() {
        String uid = UUID.randomUUID().toString();
        Student existingStudent = new Student(uid, "Bobby", 17, "IntelliJ", false, "name", null); // Creates hypothetical existing student
        existingStudent = studentRepo.save(existingStudent); // Saves that existing student to the database
    }

    @Test
    void testCanCreateCheckin(){
        //TODO: Finish Implementing this test class with the checkin DTO
    }
}
