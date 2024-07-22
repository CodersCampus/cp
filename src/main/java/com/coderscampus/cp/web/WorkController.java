package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Student;
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

    @Autowired
    public WorkController(WorkService workService) {
        this.workService = workService;

    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Work work = new Work();
        Student defaultStudent = new Student();
        defaultStudent.setUid("whateverclever");
        work.setStudent(defaultStudent);
        model.addAttribute("work", work);
//        model.addAttribute("students", studentService.findByUid(uid));
        return "work/work";
    }

    @PostMapping("/create")
    public String createWork(@ModelAttribute Work work, Model model) {
        if (work.getStudent() == null) {
            Student defaultStudent = new Student();
            defaultStudent.setUid("whateverclever");
            work.setStudent(defaultStudent);
        } else if (work.getStudent().getUid() == null || work.getStudent().getUid().isEmpty()) {
            work.getStudent().setUid("whateverclever");
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
