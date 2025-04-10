package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.;
import com.coderscampus.cp.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resume")
public class GitHubController {

    @Autowired
    private Service resumeService;

    @GetMapping("/")
    public String home(ModelMap model) {
        List<> resumes = resumeService.findAll();
        model.put("resumes", resumes);
        model.addAttribute("pageTitle", " Read");
        model.put("is", true);
        return "resume/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
         resume = new ();
        model.put("resume", resume);
        model.addAttribute("pageTitle", " Create");
        model.put("is", true);
        return "resume/create";
    }

    @PostMapping("/create")
    public String create( resume, @RequestParam("uid") String uid) {
        resume = resumeService.saveByUid(resume, uid);
        return "redirect:/resume/";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
         resume = resumeService.findById(id);
        model.put("resume", resume);
        model.addAttribute("pageTitle", " Update");
        model.put("is", true);
        return "resume/update";
    }

    @PostMapping("/update")
    public String update( resume) {
        resumeService.save(resume);
        return "redirect:/resume/";
    }

    @PostMapping("/delete")
    public String delete( resume) {
        resumeService.delete(resume);
        return "redirect:/resume/";
    }
}
