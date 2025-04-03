package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.service.LinkedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/linkedin")
public class LinkedInController {

    @Autowired
    private LinkedInService linkedInService;

    @GetMapping("")
    public String home(ModelMap model) {
        List<LinkedIn> linkedIns = linkedInService.findAll();
        model.put("linkedIns", linkedIns);
        model.addAttribute("pageTitle", "LinkedIn Read");
        model.put("isLinkedIn", true);
        return "linkedin/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        LinkedIn linkedIn = new LinkedIn();
        model.put("linkedIn", linkedIn);
        model.addAttribute("pageTitle", "LinkedIn Create");
        model.put("isLinkedIn", true);
        return "linkedin/create";
    }

    @PostMapping("/create")
    public String create(LinkedIn linkedIn, @RequestParam("uid") String uid) {
        linkedIn = linkedInService.saveByUid(linkedIn, uid);
        return "redirect:/linkedin/";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        LinkedIn linkedIn = linkedInService.findById(id);
        model.put("linkedIn", linkedIn);
        model.addAttribute("pageTitle", "LinkedIn Update");
        model.put("isLinkedIn", true);
        return "linkedin/update";
    }

    @PostMapping("/update")
    public String update(LinkedIn linkedIn) {
        linkedInService.save(linkedIn);
        return "redirect:/linkedin/";
    }

    @PostMapping("/delete")
    public String delete(LinkedIn linkedIn) {
        linkedInService.delete(linkedIn);
        return "redirect:/linkedin/";
    }
}
