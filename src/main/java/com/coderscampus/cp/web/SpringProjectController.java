package com.coderscampus.cp.web;

import java.util.List;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.dto.AuthObjectDto;
import com.coderscampus.cp.service.CheckinService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.coderscampus.cp.domain.SpringProject;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.SpringProjectRepository;
import com.coderscampus.cp.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String getDashboard(ModelMap model, HttpSession httpSession, String clientTimeZone) {
        String uid = (String)httpSession.getAttribute("uid");
        String displayName = (String)httpSession.getAttribute("displayName");
        Checkin checkin = new Checkin();
        checkin = checkinService.saveByUid(checkin, uid, clientTimeZone);
        Student student = new Student();
		model.put("student", student);
    	return "dashboard";
    }

    @PostMapping("/send-oauth")
    @ResponseBody
    public String getOauth(@RequestBody AuthObjectDto authDto,
                                      HttpSession httpSession) {
        System.out.println(authDto);
        if(authDto != null){
            httpSession.setAttribute("uid", authDto.getUid());
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
