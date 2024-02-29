package com.coderscampus.springwise.react.controller;

import com.coderscampus.springwise.domain.Student;
import com.coderscampus.springwise.service.StudentService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ComponentScan
@RestController
@RequestMapping("/api/student")
public class StudentControllerR {
    private final StudentService studentService;

    public StudentControllerR(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/get-one-student")
    public ResponseEntity<Student> getOneStudentById(
            @RequestParam Long studentId) {
        Student requestedStudent = studentService.findById(studentId);
        return ResponseEntity.ok(requestedStudent);
    }
    @GetMapping("/get-featured-students")
    public ResponseEntity<List<Student>> getFeaturedStudentList() {
        List<Student> featuredStudentList = studentService.getFeaturedStudents();
        return ResponseEntity.ok(featuredStudentList);
    }
}
