package com.coderscampus.springwise.controller;

import com.coderscampus.springwise.domain.SpringProject;
import com.coderscampus.springwise.repository.SpringProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SpringProjectController {

    @Autowired
    private SpringProjectRepository springProjectRepository;

    // testing
    @GetMapping("/springprojects")
    public String getSpringProjects(Model model) {
        List<SpringProject> projects = springProjectRepository.findAll();
        model.addAttribute("projects", projects);
        return "springprojects";
    }
}
