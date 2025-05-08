package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.LinkedInRepository;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LinkedInServiceTest {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private LinkedInService linkedInService;
    @Autowired
    private LinkedInRepository linkedInRepo;

    Student student1;
    Student student2;

    StudentDTO studentDTO1;
    StudentDTO studentDTO2;

    String student1Uid;
    String student2Uid;

    List<CheckinDTO> student1CheckinDTOList;
    List<CheckinDTO> student2CheckinDTOList;

    List<LinkedIn> student1LinkedInList;

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

        student1LinkedInList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            LinkedIn linkedIn  = new LinkedIn();
            linkedIn.setBanner("Blocker" + i);
            linkedIn.setAbout("Blocker" + i);
            linkedIn.setUrl("Blocker" + i);
            linkedIn.setFeaturedPosts("Blocker" + i);
            linkedIn.setActivity("Blocker" + i);
            linkedIn.setSkills("Blocker" + i);
            linkedIn.setEmail("Blocker" + i);
            linkedIn.setFirstName("Blocker" + i);
            linkedIn.setLastName("Blocker" + i);
            linkedIn.setBiography("Blocker" + i);
            linkedIn.setEducation("Blocker" + i);
            linkedIn.setExperience("Blocker" + i);
            linkedIn.setLocation("Blocker" + i);
            linkedIn.setImage("Blocker" + i);
            linkedIn.setTitle("Blocker" + i);
            linkedIn.setStudent(student1);
            linkedIn.getStudent().setUid(student1Uid);
            linkedInRepo.save(linkedIn);
            student1LinkedInList.add(linkedIn);
        }
    }
    @AfterEach
    void cleanUpData() {
        student1LinkedInList.forEach(linkedIn -> {
            linkedInRepo.findById(linkedIn.getId()).ifPresent(linkedInRepo::delete);
        });
        studentRepo.delete(student1);
        studentRepo.delete(student2);
    }

    @Test
    @Transactional
    void testSetUpCreateCheckIn() {
        assertEquals(4, student1LinkedInList.size());
    }



}
