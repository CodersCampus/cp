package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Profile;
import com.coderscampus.cp.domain.Resume;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.service.ProfileService;
import com.coderscampus.cp.service.ResumeService;
import com.coderscampus.cp.service.StudentService;
import com.coderscampus.cp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private static final List<String> MONTH_NAMES = List.of(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );
    private static final List<String> DEGREE_NAMES = List.of(
            "Bachelor of Arts", "Bachelor of Science", "Master of Arts", "Master of Science",
            "Doctor of Philosophy", "Associate Degree", "Certificate", "Diploma"
    );
    private static final List<Integer> YEARS = IntStream.rangeClosed(1960, 2025)
            .boxed()
            .collect(Collectors.toList());


    private final ProfileService profileService;
    private final StudentService studentService;
    private final ResumeService resumeService;
    private final UserService userService;


    public ProfileController(ProfileService profileService, StudentService studentService, ResumeService resumeService, UserService userService) {
        this.profileService = profileService;
        this.studentService = studentService;
        this.resumeService = resumeService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getProfile(ModelMap model, HttpSession session) {
        String uid = (String) session.getAttribute("uid");

        List<String> months = new ArrayList<>(MONTH_NAMES);
        List<String> degrees = new ArrayList<>(DEGREE_NAMES);
        List<Integer> years = new ArrayList<>(YEARS);

        Student student = studentService.findStudentByUid(uid);
        Profile profile = profileService.findById(student.getId());
        Resume resume = resumeService.findById(student.getId());

        model.put("months", months);
        model.put("degrees", degrees);
        model.put("years", years);

//        model.addAttribute("section", section);
        model.put("profile", profile);
        model.put("resume", resume);

        return "profile/index";
    }

    @PostMapping("/update")
    public String putProfile(Profile profile) {
        profileService.save(profile);
        return "redirect:/profile";
    }
}
