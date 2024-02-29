package com.coderscampus.cp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coderscampus.cp.domain.SpringProject;
import com.coderscampus.cp.repository.SpringProjectRepository;

@Controller
public class SpringProjectController {

    @Autowired
    private SpringProjectRepository springProjectRepository;
  

    
    @GetMapping("/")
    public String getIndex() {
    	
    	return "index";
    } 
    
    @GetMapping("/springprojects")
    public String getSpringProjects(Model model) {
        List<SpringProject> projects = springProjectRepository.findAll();
        model.addAttribute("projects", projects);
        return "springprojects";
    }
}
