package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.*;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.SpringProjectRepository;
import com.coderscampus.cp.service.*;
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

@Controller
public class SpringProjectController {
    @Value("${show.database.console.link}")
    private boolean showDatabaseConsoleLink;

    private final SpringProjectRepository springProjectRepository;
    private final StudentService studentService;
    private final ProfileService profileService;
    private final ResumeService resumeService;
    private final LinkedInService linkedInService;

    public SpringProjectController(SpringProjectRepository springProjectRepository, StudentService studentService, ProfileService profileService, ResumeService resumeService, LinkedInService linkedInService) {
        this.springProjectRepository = springProjectRepository;
        this.studentService = studentService;
        this.profileService = profileService;
        this.resumeService = resumeService;
        this.linkedInService = linkedInService;
    }

    @GetMapping("/")
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

            System.out.println("uid: " + httpSession.getAttribute("uid"));
            System.out.println("email: " + httpSession.getAttribute("email"));
            System.out.println("displayName: " + httpSession.getAttribute("displayName"));

            Student student = studentService.findStudentByUid(authDto.getUid());
            if (student == null) {
                student = createStudentWithAssociatedEntities(authDto);
                StudentDTO studentDTO = new StudentDTO(student);
                System.out.println("StudentDTO: " + studentDTO);
            } else {
                ensureProfileExists(student);
                ensureResumeExists(student);
                ensureLinkedInExists(student);
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

    private Student createStudentWithAssociatedEntities(AuthObjectDTO authDto) {
        Student student = new Student();
        student.setUid(authDto.getUid());
        student.setName(authDto.getDisplayName());
        student = studentService.save(student);

        ensureProfileExists(student);
        ensureResumeExists(student);
        ensureLinkedInExists(student);

        return student;
    }

    private void ensureProfileExists(Student student) {
        Profile profile = profileService.findById(student.getId());
        if (profile == null) {
            profile = new Profile();
            profile.setStudent(student);
            profileService.save(profile);
        }
    }

    private void ensureResumeExists(Student student) {
        Resume resume = resumeService.findById(student.getId());
        if (resume == null) {
            resume = new Resume();
            resume.setStudent(student);
            resumeService.save(resume);
        }
    }

    private void ensureLinkedInExists(Student student) {
        LinkedIn linkedIn = linkedInService.findById(student.getId());
        if (linkedIn == null) {
            linkedIn = new LinkedIn();
            linkedIn.setStudent(student);
            linkedInService.save(linkedIn);
        }
    }
}
