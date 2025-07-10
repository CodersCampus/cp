package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.*;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.SpringProjectRepository;
import com.coderscampus.cp.service.StudentService;
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
    /**
     * private final StudentService studentService;
     * private final CheckinService checkinService;
     * <p>
     * public SpringProjectController(SpringProjectRepository springProjectRepository, StudentService studentService, CheckinService checkinService) {
     * this.springProjectRepository = springProjectRepository;
     * this.studentService = studentService;
     * this.checkinService = checkinService;
     * }
     */
    private final StudentService studentService;

    public SpringProjectController(SpringProjectRepository springProjectRepository, StudentService studentService) {
        this.springProjectRepository = springProjectRepository;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String getDashboard(ModelMap model, HttpSession httpSession) {
        String userEmail = (String) httpSession.getAttribute("email");
        String uid = (String) httpSession.getAttribute("uid");
        String displayName = (String) httpSession.getAttribute("displayName");
        Student student = new Student();
        model.put("student", student);
        model.put("displayName", displayName);
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
                student = new Student();
                student.setUid(authDto.getUid());
                student.setName(authDto.getDisplayName());
                studentService.save(student);
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

}
