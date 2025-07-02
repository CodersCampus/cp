package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.*;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.SpringProjectRepository;
import com.coderscampus.cp.service.ProfileService;
import com.coderscampus.cp.service.ResumeService;
import com.coderscampus.cp.service.StudentService;
import com.coderscampus.cp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class SpringProjectController {
    private final UserService userService;
    @Value("${show.database.console.link}")
    private boolean showDatabaseConsoleLink;

    private final SpringProjectRepository springProjectRepository;
    private final StudentService studentService;
    private final ProfileService profileService;
    private final ResumeService resumeService;

    public SpringProjectController(SpringProjectRepository springProjectRepository, StudentService studentService, ProfileService profileService, ResumeService resumeService, UserService userService) {
        this.springProjectRepository = springProjectRepository;
        this.studentService = studentService;
        this.profileService = profileService;
        this.resumeService = resumeService;
        this.userService = userService;
    }

    @GetMapping("/old-dashboard")
    public String getDashboard(ModelMap model, HttpSession httpSession) {
        String userEmail = (String) httpSession.getAttribute("email");
        String uid = (String) httpSession.getAttribute("uid");
        String displayName = (String) httpSession.getAttribute("displayName");
        Student student = new Student();
        model.put("student", student);
        model.addAttribute("showDatabaseConsoleLink", showDatabaseConsoleLink);
        return "dashboard";
    }

    @PostMapping("/send-oauth")
    @ResponseBody
    public String getOauth(@RequestBody AuthObjectDTO authDto, HttpSession httpSession) {
        if (authDto != null) {
            httpSession.setAttribute("uid", authDto.getUid());
            httpSession.setAttribute("email", authDto.getEmail());
            httpSession.setAttribute("displayName", authDto.getDisplayName());

            Student student = studentService.findStudentByUid(authDto.getUid());
            if (student == null) {
                createNewStudentWithAssociatedEntities(authDto);
            } else {
                ensureProfileExists(student);
                ensureResumeExists(student);
            }
        }

        return "redirect:/";
    }

    @GetMapping("/springprojects")
    public String getSpringProjects(Model model) {
        List<SpringProject> projects = springProjectRepository.findAll();
        model.addAttribute("projects", projects);
        return "springprojects";
    }

    private Student createNewStudentWithAssociatedEntities(AuthObjectDTO authDto) {
        Student newStudent = new Student();
        newStudent.setUid(authDto.getUid());
        newStudent.setName(authDto.getDisplayName());
        newStudent.setEmail(authDto.getEmail());
        Student savedStudent = studentService.save(newStudent);

        Profile profile = new Profile();
        profile.setStudent(savedStudent);
        Profile savedProfile = profileService.save(profile);
        savedStudent.setProfile(savedProfile);

        Resume resume = new Resume();
        resume.setStudent(savedStudent);
        Resume savedResume = resumeService.save(resume);
        savedStudent.setResume(savedResume);

        return savedStudent;
    }

    private void ensureProfileExists(Student student) {
        if (student.getProfile() == null || profileService.findById(student.getProfile().getId()) == null) {
            Profile profile = new Profile();
            profile.setStudent(student);
            Profile savedProfile = profileService.save(profile);
            student.setProfile(savedProfile);
            studentService.save(student);
        }
    }

    private void ensureResumeExists(Student student) {
        if (student.getResume() == null || resumeService.findById(student.getResume().getId()) == null) {
            Resume resume = new Resume();
            resume.setStudent(student);
            Resume savedResume = resumeService.save(resume);
            student.setResume(savedResume);
            studentService.save(student);
        }
    }

}
