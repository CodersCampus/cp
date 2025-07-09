package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/send-oauth")
    @ResponseBody
    public String getOauth(@RequestBody AuthObjectDTO authDto, HttpSession session) {
        if (authDto != null) {
            // session.setAttribute("uid", authDto.getUid());
            // session.setAttribute("email", authDto.getEmail());
            // session.setAttribute("displayName", authDto.getDisplayName());
            Student student = studentService.findStudentByUid(authDto.getUid());
            if (student == null) {
                student = new Student();
                student.setUid(authDto.getUid());
                student.setName(authDto.getDisplayName());
                studentService.save(student);
                StudentDTO studentDTO = new StudentDTO(student);
            }
            session.setAttribute("student", new StudentDTO(student));
        } else {
            throw new RuntimeException("AuthObjectDTO is null");
        }

        return "redirect:/";
    }


}
