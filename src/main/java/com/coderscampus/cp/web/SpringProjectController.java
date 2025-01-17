package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.SpringProject;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.repository.SpringProjectRepository;
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

    private final SpringProjectRepository springProjectRepository;

    @Value("${show.database.console.link}")
    private boolean showDatabaseConsoleLink;
    /**
    private final StudentService studentService;
    private final CheckinService checkinService;

    public SpringProjectController(SpringProjectRepository springProjectRepository, StudentService studentService, CheckinService checkinService) {
        this.springProjectRepository = springProjectRepository;
        this.studentService = studentService;
        this.checkinService = checkinService;
    }
    */

    public SpringProjectController(SpringProjectRepository springProjectRepository) {
        this.springProjectRepository = springProjectRepository;
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
