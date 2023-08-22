package com.coderscampus.springwise.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.springwise.domain.Student;
import com.coderscampus.springwise.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String home(ModelMap model) {
		List<Student> students = studentService.findAll();
		model.put("students", students);
		return "student/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		Student student = new Student();
	
		model.put("student", student);
		return "student/create";
	}

	@PostMapping("/create")
	public String create(Student student) {
		System.out.println("Students:" + student);
		studentService.save(student);
	
		return "redirect:/student/";
	}
// This is same mapping as Student Home page-->left here just in case.(may need deleted)
//	@GetMapping("/read")
//	public String read(ModelMap model) {
//		List<Student> students = studentService.findAll();
//		model.put("students", students);
//		return "student/read";
//	}
	
	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Student student = studentService.findById(id);
		model.put("student", student);
		System.out.println("student:" + student);
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
