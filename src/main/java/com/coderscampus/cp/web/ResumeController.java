package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Resume;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.service.ResumeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static com.coderscampus.cp.web.WebController.isAuthenticated;

@Controller
@RequestMapping("/resume")
public class  ResumeController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping("")
    public String home(ModelMap model, HttpSession httpSession) {
        UserDTO currentUser = (UserDTO) httpSession.getAttribute("currentUser");
        System.out.println("CURRENT USER in Resume: " + currentUser);

        List<Resume> resumes = resumeService.findAll();
        model.put("resumes", resumes);
        model.addAttribute("pageTitle", "Resume Read");
        model.put("isResume", true);
        return "resume/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        Resume resume = new Resume();
        model.put("resume", resume);
        model.addAttribute("pageTitle", "Resume Create");
        model.put("isResume", true);
        return "resume/create";
    }

    @PostMapping("/create")
    public String create(Resume resume, @RequestParam("uid") String uid) {
        // Check and see if the student exists
        // if not create it
        resume = resumeService.saveByUid(resume, uid);
        return "redirect:/resume";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        Resume resume = resumeService.findById(id);

        if (resume.getStudent() != null && resume.getStudent().getUid().equals(uid)) {
            model.put("resume", resume);
            model.addAttribute("pageTitle", "Resume Update");
            model.put("isResume", true);
            return "resume/update";
        } else {
            return "redirect:/resume";
        }
    }

    @PostMapping("/update")
    public String update(Resume resume, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        resumeService.saveByUid(resume, uid);
        return "redirect:/profile";
    }

    @PostMapping("/delete")
    public String delete(Resume resume) {
        resumeService.delete(resume);
        return "redirect:/resume";
    }
}
