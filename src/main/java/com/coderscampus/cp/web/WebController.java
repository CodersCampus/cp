package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.service.ProfileService;
import com.coderscampus.cp.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.service.SessionManagerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class WebController {
    @Value("${show.database.console.link}")
    private boolean showDatabaseConsoleLink;

    private final SessionManagerService sessionManagerService;
    private final UserService userService;
    private final ProfileService profileService;

    public WebController(SessionManagerService sessionManagerService, UserService userService, ProfileService profileService) {
        this.sessionManagerService = sessionManagerService;
        this.userService = userService;
        this.profileService = profileService;
    }

    @GetMapping("")
    public String dashboardView(ModelMap model, HttpSession session) {
        if (!sessionManagerService.isAuthenticated(session)) {
            return "error/401"; // Redirect to the error page if not authenticated
        }

        UserDTO user = sessionManagerService.getCurrentUser(session);
        System.out.println("User authenticated: " + user);

        if (user != null) {
            model.addAttribute("displayName", user.getDisplayName());
        }

        return "dashboard/index";
    }

    @GetMapping("/support")
    public String supportView() {
        return "dashboard/support"; // This will resolve to src/main/resources/templates/dashboard/support.html
    }

    @GetMapping("/documentation")
    public String documentationView() {
        return "dashboard/documentation"; // This will resolve to src/main/resources/templates/dashboard/documentation.html
    }

    @PostMapping("/send-oauth")
    @ResponseBody
    public String getOauth(@RequestBody AuthObjectDTO authDto, HttpSession session) {
        try {
            User user = userService.getOrCreateUser(authDto);
            UserDTO userDTO = sessionManagerService.authenticate(user, session);
            // Ensure that the user profile is created or updated
            profileService.ensureUserProfileExists(user);

            System.out.println("User UID in session: " + authDto.getUid());
            System.out.println("User DTO in session: " + userDTO);

            // Check if the user is authenticated
            if (!sessionManagerService.isAuthenticated(session)) {
                return "redirect:/error/401"; // Redirect to the error page if not authenticated
            }

            return "redirect:/";
        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
            return "redirect:/error";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        sessionManagerService.logout(session);
        return "redirect:/";
    }
}
