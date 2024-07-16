package com.coderscampus.cp.web;

import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.coderscampus.cp.domain.Work;
import com.coderscampus.cp.service.WorkService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/works")
public class WorkController {

    private final WorkService workService;
	private final StudentService studentService;

    @Autowired
    public WorkController(WorkService workService, StudentService studentService) {
        this.workService = workService;
        this.studentService = studentService;


    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("work", new Work());
//        model.addAttribute("students", studentService.findByUid(uid));
        return "work/work";
    }

    @PostMapping("/create")
    public String createWork(@ModelAttribute Work work, Model model) {
//        StudentDTO studentDTO = studentService.saveByUid(uid);
        workService.saveWork(work);
		model.addAttribute("message", "Work entry created successfully");
        return "redirect:/works";
    }

    @GetMapping("/{id}")
    public String getWorkById(@PathVariable Long id, Model model) {
        workService.findWorkById(id).ifPresent(work -> model.addAttribute("work", work));
        return "work/work-details";
    }

}
