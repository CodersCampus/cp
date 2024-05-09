package com.coderscampus.cp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.service.StudentService;

import jakarta.servlet.http.HttpSession;

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
        if (uid != null && !uid.isEmpty()) {
            StudentDTO studentDTO = studentService.findByUid(uid);
            model.addAttribute("student", studentDTO);
            model.addAttribute("isStudent", true);
        }
        model.addAttribute("pageTitle", "Student Read");
        return "student/read";
    }



    @PostMapping("/create")
    public String create(StudentDTO student, @RequestParam("uid") String uid) {
        studentService.saveByUid(student, uid);
        return "redirect:/student/";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        StudentDTO studentDTO = studentService.findById(id);
        model.put("student", studentDTO);
        model.addAttribute("pageTitle", "Student Update");
        model.put("isStudent", true);
        return "student/update";
    }

    @PostMapping("/update")
	public String update(@ModelAttribute("student") StudentDTO studentDTO, @RequestParam("uid") String uid) {
		studentService.updateStudent(studentDTO, uid);
		return "redirect:/student/";
	}

    @PostMapping("/delete")
    public String delete(Student student) {
        studentService.delete(student);
        return "redirect:/student/";

    }
}
