package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {


    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/")
    public String home(ModelMap model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        String displayName = (String) httpSession.getAttribute("displayName");
        if (uid != null && !uid.isEmpty()) {
            Student student = studentService.findByUid(uid);
            System.out.println(student);
            model.put("student", student);
            model.put("isStudent", true);
        }
        model.addAttribute("pageTitle", "Student Read");
        return "student/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        Student student = new Student();
        model.put("student", student);
        model.addAttribute("pageTitle", "Student Create");
        model.put("isStudent", true);
        return "student/create";
    }

    @PostMapping("/create")
    public String create(Student student, @RequestParam("uid") String uid) {
        studentService.saveByUid(student, uid);
        return "student/checkuid";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        Student student = studentService.findById(id);
        model.put("student", student);
        model.addAttribute("pageTitle", "Student Update");
        model.put("isStudent", true);
        return "student/update";
    }

    @PostMapping("/update")
    public String update(Student student) {
        studentService.save(student);
        return "redirect:/student/";
    }

    @PostMapping("/delete")
    public String delete(Student student) {
        studentService.delete(student);
        return "redirect:/student/";

    }
}
