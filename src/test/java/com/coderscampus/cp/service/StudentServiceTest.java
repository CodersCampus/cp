package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc



class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testIsValidStudentUpdate() {
        String uid = UUID.randomUUID().toString();
        Student existingStudent = new Student(0, "bobby", 17, uid);
        existingStudent = studentRepo.save(existingStudent);
        Student student = new Student(existingStudent.getId(), "bobby", 12, uid);
        assertTrue(studentService.doesStudentExistInRepository(student));
        studentRepo.delete(existingStudent);
    }


    @Test
    void testIsValidNewStudent() {
        String uid = UUID.randomUUID().toString();
        Student student = new Student(0, "bobby", 12, uid);
        assertTrue(studentService.isValidNewStudent(student));
        studentRepo.delete(student);

    }


    @Test
    void testValidNewStudentIfExists() {
        String uid = UUID.randomUUID().toString();
        Student student = new Student(0, "bobby", 12, uid);
        Student existingStudent = new Student(0, "bobby", 17, uid);
        studentRepo.save(existingStudent);
        assertFalse(studentService.isValidNewStudent(student));
        studentRepo.delete(existingStudent);
        studentRepo.delete(student);
    }


    @Test
    void testCanAddSecondStudent() {
        String uid = UUID.randomUUID().toString();
        String uid2 = UUID.randomUUID().toString();
        Student student2 = new Student(1, "bobby", 12, uid2);
        Student student = new Student(0, "bobby", 12, uid);
        studentRepo.save(student);
        studentRepo.save(student2);
        assertFalse(studentService.isValidNewStudent(student));
        assertFalse(studentService.isValidNewStudent(student2));
        studentRepo.delete(student2);
        studentRepo.delete(student);
    }

    @Test
    void testStudentSaveByUid(){
        String uid = UUID.randomUUID().toString();
        Student student = new Student(554, "bobby", 12, uid);
        StudentDTO studentDTO = new StudentDTO(student);
        StudentDTO result = studentService.saveByUid(studentDTO, uid);
        assertEquals("bobby", result.getName());
        Student studentResult = studentRepo.findByUid(uid);
        assertEquals("bobby", studentResult.getName());
    }

    @Test
    public void testGetCreate() throws Exception {
        StudentDTO student = new StudentDTO();
        student.setName("bobby");
        String uid = UUID.randomUUID().toString();

        // Perform a POST request to your endpoint with the student object and uid parameter
        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
                        .param("uid", uid))
                .andExpect(status().isOk()); // Assuming the endpoint returns 200 OK upon successful creation
    }

}
