package com.coderscampus.springwise.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coderscampus.springwise.domain.SpringProject;
import com.coderscampus.springwise.repository.SpringProjectRepository;
import com.coderscampus.springwise.service.SeedService;

@Controller
public class SpringProjectController {

    @Autowired
    private SpringProjectRepository springProjectRepository;
    @Autowired
    private SeedService seedService;

    
    @GetMapping("/")
    public String getIndex() {
    	seedService.populateData();
    	
    	return "index";
    }
    
    @GetMapping("/springprojects")
    public String getSpringProjects(Model model) {
        List<SpringProject> projects = springProjectRepository.findAll();
        model.addAttribute("projects", projects);
        return "springprojects";
    }
}
