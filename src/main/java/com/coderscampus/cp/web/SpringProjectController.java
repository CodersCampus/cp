package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.SpringProject;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.StudentDTO;
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
public class SpringProjectController extends WebController {
    @Value("${show.database.console.link}")
    private boolean showDatabaseConsoleLink;

    private final SpringProjectRepository springProjectRepository;

    public SpringProjectController(SpringProjectRepository springProjectRepository) {
        super();
        this.springProjectRepository = springProjectRepository;
    }

    @GetMapping("/")
    public String getDashboard(ModelMap model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        String userEmail = (String) httpSession.getAttribute("email");
        String displayName = (String) httpSession.getAttribute("displayName");
        StudentDTO studentDTO = (StudentDTO) httpSession.getAttribute("student");

        System.out.println("UID: " + uid);
        System.out.println("User Email: " + userEmail);
        System.out.println("Display Name: " + displayName);
        System.out.println("Student DTO: " + studentDTO);

        Student student = new Student();
        model.put("student", student);
        model.addAttribute("showDatabaseConsoleLink", showDatabaseConsoleLink);
        return "dashboard";
    }

    @PostMapping("/send-oauth")
    @ResponseBody
    public String sendOAuth(@RequestBody AuthObjectDTO authObjectDTO, HttpSession httpSession) {
        System.out.println("Received OAuth Object: " + authObjectDTO);
        httpSession.setAttribute("uid", authObjectDTO.getUid());
        httpSession.setAttribute("email", authObjectDTO.getEmail());
        httpSession.setAttribute("displayName", authObjectDTO.getDisplayName());

        return "redirect:/";
    }

    @GetMapping("/springprojects")
    public String getSpringProjects(Model model) {
        List<SpringProject> projects = springProjectRepository.findAll();
        model.addAttribute("projects", projects);
        return "springprojects";
    }
}
