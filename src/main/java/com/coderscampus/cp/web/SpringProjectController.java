package com.coderscampus.cp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.coderscampus.cp.domain.SpringProject;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.SpringProjectRepository;
import com.coderscampus.cp.service.StudentService;

@Controller
public class SpringProjectController {

    @Autowired
    private SpringProjectRepository springProjectRepository;
    
    @Autowired
    private StudentService studentService;

    
    @GetMapping("/")
    public String getDashboard(ModelMap model) {
    	Student student = new Student();
		model.put("student", student);
    	return "dashboard";
    } 
    
    @GetMapping("/springprojects")
    public String getSpringProjects(Model model) {
        List<SpringProject> projects = springProjectRepository.findAll();
        model.addAttribute("projects", projects);
        return "springprojects";
    }
}
