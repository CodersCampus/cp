package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.SpringProject;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.repository.SpringProjectRepository;
import com.coderscampus.cp.service.SessionManagerService;
import com.coderscampus.cp.service.StudentService;
import com.coderscampus.cp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springproject")
public class SpringProjectController {

    private final SpringProjectRepository springProjectRepository;
    private final StudentService studentService;
    private final UserService userService;
    private final SessionManagerService sessionManagerService;

    @Value("${show.database.console.link}")
    private boolean showDatabaseConsoleLink;


    public SpringProjectController(
            SpringProjectRepository springProjectRepository,
            StudentService studentService,
            UserService userService,
            SessionManagerService sessionManagerService) {
        this.springProjectRepository = springProjectRepository;
        this.studentService = studentService;
        this.userService = userService;
        this.sessionManagerService = sessionManagerService;
    }

    @GetMapping("/")
    public String getDashboard(ModelMap model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        UserDTO user = (UserDTO) httpSession.getAttribute("currentUser");

        // Add null check to prevent NullPointerException
        if (user != null) {
            model.addAttribute("displayName", user.getDisplayName());
        }
        // Authenticated user
        Student student = new Student();
        model.put("student", student);
//        model.put("displayName", user.getDisplayName());
        model.put("pageTitle", "Dashboard");
        model.put("showDatabaseConsoleLink", showDatabaseConsoleLink);

        return "dashboard/index";
    }

    @PostMapping("/send-oauth")
    @ResponseBody
    public String getOauth(@RequestBody AuthObjectDTO authDto, HttpSession httpSession) {
        try {
            // httpSession.setAttribute("uid", authDto.getUid());
            // httpSession.setAttribute("email", authDto.getEmail());
            // httpSession.setAttribute("displayName", authDto.getDisplayName());

            createdStudent(authDto);

            System.out.println("User UID in session: " + authDto.getUid());

            return "redirect:/";
        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
            return "redirect:/error";
        }
    }

    private void createdStudent(AuthObjectDTO authDto) {
        Student student = studentService.findStudentByUid(authDto.getUid());
        if (student == null) {
            student = new Student();
            student.setUid(authDto.getUid());
            student.setName(authDto.getDisplayName());
            studentService.save(student);
        }
    }

    @GetMapping("/springprojects")
    public String getSpringProjects(Model model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        UserDTO user = (UserDTO) httpSession.getAttribute("currentUser");

        List<SpringProject> projects = springProjectRepository.findAll();
        model.addAttribute("projects", projects);
        model.addAttribute("pageTitle", "Spring Projects");
        return "springprojects";
    }

    // Authentication is now handled in WebController
}
