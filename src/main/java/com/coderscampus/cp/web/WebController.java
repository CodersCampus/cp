package com.coderscampus.cp.web;

import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.service.SessionManagerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
    @Value("${show.database.console.link}")
    private boolean showDatabaseConsoleLink;

    private final SessionManagerService sessionManagerService;

    public WebController(SessionManagerService sessionManagerService) {
        this.sessionManagerService = sessionManagerService;
    }

    @GetMapping("/")
    public String dashboardView(ModelMap model, HttpSession httpSession) {
        if (!sessionManagerService.isAuthenticated(httpSession)) {
            return "dashboard/index";
        }

        UserDTO user = sessionManagerService.getCurrentUser(httpSession);
        System.out.println("User authenticated: " + user);

        // Add null check to prevent NullPointerException
        if (user != null) {
            model.addAttribute("displayName", user.getDisplayName());
        }

        return "dashboard/index";
    }

    @GetMapping("/support")
    public String support() {
        return "dashboard/support"; // This will resolve to src/main/resources/templates/dashboard/support.html
    }

    @GetMapping("/documentation")
    public String documentation() {
        return "dashboard/documentation"; // This will resolve to src/main/resources/templates/dashboard/documentation.html
    }

    @PostMapping("/send-oauth")
    @ResponseBody
    public String getOauth(@RequestBody AuthObjectDTO authDto, HttpSession httpSession) {
        try {
            UserDTO userDTO = sessionManagerService.authenticate(authDto, httpSession);

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
        sessionManagerService.logout(httpSession);
        return "redirect:/";
    }
}
