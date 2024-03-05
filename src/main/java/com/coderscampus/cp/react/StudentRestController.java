package com.coderscampus.cp.react;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@ComponentScan
@RestController
@RequestMapping("/api/student")
public class StudentRestController {
    private final ObjectMapper objectMapper;
    private final StudentService studentService;

    public StudentRestController(ObjectMapper objectMapper, StudentService studentService) {
        this.objectMapper = objectMapper;
        this.studentService = studentService;
    }
    @GetMapping("/get-one-student")
    public ResponseEntity<Student> getOneStudentById(
            @RequestParam Long studentId) {
        Student requestedStudent = studentService.findById(studentId);
        System.out.println("Returning get-one-student: " + requestedStudent);
        return ResponseEntity.ok(requestedStudent);
    }
    @GetMapping("/get-featured-students")
    public ResponseEntity<List<Student>> getFeaturedStudentList() {
//        List<Student> featuredStudentList = studentService.getFeaturedStudents();
//        return ResponseEntity.ok(featuredStudentList);
        return null;
    }
    @PostMapping("/create-student")
    public ResponseEntity<Student> createStudent(@RequestBody String rawJson) throws JsonProcessingException {
        System.out.println("Received JSON: " + rawJson);
        // Convert rawJson back to Student object manually for debugging purposes
        Student createStudentRequest = objectMapper.readValue(rawJson, Student.class);
        System.out.println("Create student request name: " + createStudentRequest.getName());
        Student newStudent = studentService.save(createStudentRequest);
        return ResponseEntity.ok(newStudent);
    }
    @PostMapping("/update-student-photo")
    public ResponseEntity<Student> updatePhoto(
            @RequestParam("studentId") Long studentId,
            @RequestParam("studentImage") MultipartFile studentImage) throws IOException {
        System.out.println("Create student request id: " + studentId);
        Student updatedStudent = studentService.updateStudentPhoto(studentId, studentImage.getBytes());
        return ResponseEntity.ok(updatedStudent);
    }
}
