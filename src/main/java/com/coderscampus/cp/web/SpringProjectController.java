package com.coderscampus.cp.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.cp.domain.SpringProject;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.repository.SpringProjectRepository;
import com.coderscampus.cp.service.CheckinService;
import com.coderscampus.cp.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SpringProjectController {

    private SpringProjectRepository springProjectRepository;
    private StudentService studentService;
    private CheckinService checkinService;

    public SpringProjectController(SpringProjectRepository springProjectRepository, StudentService studentService, CheckinService checkinService) {
        this.springProjectRepository = springProjectRepository;
        this.studentService = studentService;
        this.checkinService = checkinService;
    }

    @GetMapping("/")
    public String getDashboard(ModelMap model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        String displayName = (String) httpSession.getAttribute("displayName");
//        Checkin checkin = new Checkin();
//        checkin = checkinService.saveByUid(checkin, uid);
        Student student = new Student();
        model.put("student", student);
        return "dashboard";
    }

    @PostMapping("/send-oauth")
    @ResponseBody
    public String getOauth(@RequestBody AuthObjectDTO authDto,
                           HttpSession httpSession) {
        if (authDto != null) {
            httpSession.setAttribute("uid", authDto.getUid());
            httpSession.setAttribute("displayName", authDto.getDisplayName());
//            DO NOT NEED FOR #512 TO WORK
//            httpSession.setAttribute("photoURL", authDto.getPhotoURL()); // Add this line
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
