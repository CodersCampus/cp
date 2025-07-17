package com.coderscampus.cp.web;

import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.service.SessionManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class WebController {

    private final SessionManager sessionManager;

    public WebController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @GetMapping("/")
    public String dashboardView(ModelMap model, HttpSession httpSession) {
        if (!sessionManager.isAuthenticated(httpSession)) {
            return "dashboard/index";
        }

        UserDTO user = sessionManager.getCurrentUser(httpSession);
        System.out.println("User authenticated: " + user);

        // Add null check to prevent NullPointerException
        if (user != null) {
            model.addAttribute("displayName", user.getDisplayName());
        }

        return "dashboard/index";
    }

    @PostMapping("/send-oauth")
    @ResponseBody
    public String getOauth(@RequestBody AuthObjectDTO authDto, HttpSession httpSession) {
        try {
            UserDTO userDTO = sessionManager.authenticate(authDto, httpSession);

            System.out.println("User UID in session: " + authDto.getUid());
            System.out.println("User DTO in session: " + userDTO);

            return "redirect:/";
        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
            return "redirect:/error";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession) {
        sessionManager.logout(httpSession);
        return "redirect:/";
    }
}