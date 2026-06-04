package com.coderscampus.cp.web.api;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ApiSessionService {

    private final StudentService studentService;

    public ApiSessionService(StudentService studentService) {
        this.studentService = studentService;
    }

    public String requireUid(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        if (!StringUtils.hasText(uid)) {
            throw new UnauthorizedException();
        }
        return uid;
    }

    public Student requireStudent(HttpSession session) {
        String uid = requireUid(session);
        Student student = studentService.findStudentByUid(uid);
        if (student == null) {
            throw new UnauthorizedException();
        }
        return student;
    }
}
