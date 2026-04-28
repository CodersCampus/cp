package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringProjectController {

    @Value("${show.database.console.link}")
    private boolean showDatabaseConsoleLink;

    private final StudentService studentService;

    public SpringProjectController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String getDashboard(ModelMap model, HttpSession httpSession) {
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
            boolean hasUid = StringUtils.hasText(authDto.getUid());
            boolean hasDisplayName = StringUtils.hasText(authDto.getDisplayName());

            if (student == null && hasUid && hasDisplayName) {
                student = new Student();
                student.setUid(authDto.getUid());
                student.setName(authDto.getDisplayName());
                studentService.save(student);
            } else if (student != null && hasDisplayName && !StringUtils.hasText(student.getName())) {
                student.setName(authDto.getDisplayName());
                studentService.save(student);
            }
        }
        return "redirect:/";
    }

}
