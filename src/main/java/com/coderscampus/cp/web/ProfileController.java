package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Profile;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.service.ProfileService;
import com.coderscampus.cp.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final StudentService studentService;

    public ProfileController(ProfileService profileService, StudentService studentService) {
        this.profileService = profileService;
        this.studentService = studentService;
    }

    @GetMapping("")
    public String profileView(ModelMap model, HttpSession session) {
        System.out.println(session.getAttribute("uid"));
        System.out.println(session.getAttribute("email"));
        return "profile/index";
    }
}
