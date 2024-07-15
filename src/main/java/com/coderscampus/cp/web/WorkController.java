package com.coderscampus.cp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("work", new Work());
		return "create-work";
	}

	@PostMapping("/create")
	public String createWork(@ModelAttribute Work work, Model model) {
		workService.saveWork(work);
		model.addAttribute("message", "Work entry created successfully");
		return "redirect:/works";
	}

	@GetMapping("/{id}")
	public String getWorkById(@PathVariable Long id, Model model) {
		workService.findWorkById(id).ifPresent(work -> model.addAttribute("work", work));
		return "work-details";
	}
}
