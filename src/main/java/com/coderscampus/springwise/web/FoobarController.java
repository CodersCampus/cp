package com.coderscampus.springwise.web;

import com.coderscampus.springwise.domain.Foobar;
import com.coderscampus.springwise.service.FoobarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/foobar")
public class FoobarController {

	@Autowired
	private FoobarService foobarService;

	@GetMapping("/")
	public String home(ModelMap model) {
		List<Foobar> foobars = foobarService.findAll();
		model.put("foobars", foobars);
		model.addAttribute("pageTitle", "Foobar Read");
		model.put("isFoobar", true);
		return "foobar/read";
	}

	@GetMapping("/create")
	public String getCreate(ModelMap model) {
		Foobar foobar = new Foobar();
		model.put("foobar", foobar);
		model.addAttribute("pageTitle", "Foobar Create");
		model.put("isFoobar", true);
		return "foobar/create";
	}

	@PostMapping("/create")
	public String create(Foobar foobar, @RequestParam("uid") String uid) {
		System.out.println("in/create" + foobar + uid);
//		foobarService.save(foobar); 
// coming back to this after issue329, we're making a separate issue.
		return "redirect:/foobar/";
	}

	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Foobar foobar = foobarService.findById(id);
		model.put("foobar", foobar);
		model.addAttribute("pageTitle", "Foobar Update");
		model.put("isFoobar", true);
		return "foobar/update";
	}

	@PostMapping("/update")
	public String update(Foobar foobar) {
		foobarService.save(foobar);
		return "redirect:/foobar/";
	}

	@PostMapping("/delete")
	public String delete(Foobar foobar) {
		foobarService.delete(foobar);
		return "redirect:/foobar/";
	}
}
