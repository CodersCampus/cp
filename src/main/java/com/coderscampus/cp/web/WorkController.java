package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.coderscampus.cp.domain.Work;
import com.coderscampus.cp.service.WorkService;

@Controller
@RequestMapping("/works")
public class WorkController {

    private final WorkService workService;

    @Autowired
    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Work work = new Work();
        work.setStudentName(work.getStudentName()); // Set a default name if needed
        model.addAttribute("work", work);
        return "work/work";
    }

    @PostMapping("/create")
    public String createWork(@ModelAttribute Work work, Model model) {

        if (work.getStudentName() != null && work.getStudentName().startsWith(",")) {
            work.setStudentName(work.getStudentName().substring(1).trim());
        }

        if (work.getStudentName() == null || work.getStudentName().isEmpty()) {
            work.setStudentName(work.getStudentName());
        }

        workService.saveWork(work);
        model.addAttribute("message", "Work entry created successfully");
        return "redirect:/works/create";
    }

    @GetMapping("/{id}")
    public String getWorkById(@PathVariable Long id, Model model) {
        workService.findWorkById(id).ifPresent(work -> model.addAttribute("work", work));
        return "work/work-details";
    }
}
