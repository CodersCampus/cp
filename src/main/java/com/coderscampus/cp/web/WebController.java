package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@Controller
@RequestMapping("/")
public class WebController {
    @Value("${show.database.console.link}")
    private boolean showDatabaseConsoleLink;

    private final UserService userService;

    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getDashboard(ModelMap model, HttpSession session) {
        String userEmail = (String) session.getAttribute("email");
        String uid = (String) session.getAttribute("uid");
        String displayName = (String) session.getAttribute("displayName");

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
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password");
        }
        model.addAttribute("user", new UserDTO());
        return "auth/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserDTO userDTO, HttpSession session) {
        try {
            // Check if user exists with the provided email
            if (!userService.existsByEmail(userDTO.getEmail())) {
                return "{\"success\": false, \"message\": \"Invalid email or password\"}";
            }

            // Get the user and verify password (in a real app, you'd use password encoding)
            User user = userService.findByEmail(userDTO.getEmail());

            // In a real application, you would use a password encoder to compare passwords
            // For example: passwordEncoder.matches(userDTO.getPassword(), user.getPassword())
            if (user.getPassword() == null || !user.getPassword().equals(userDTO.getPassword())) {
                return "{\"success\": false, \"message\": \"Invalid email or password\"}";
            }

            // Set user session attributes
            session.setAttribute("uid", user.getUid());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("displayName", user.getDisplayName());

            // Update user's online status
            user.setOnline(true);
            userService.create(user);

            return "{\"success\": true, \"redirect\": \"/\"}";
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"An error occurred: " + e.getMessage() + "\"}";
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody UserDTO userDTO, HttpSession session) {
        try {
            // Check if user already exists
            if (userService.existsByEmail(userDTO.getEmail())) {
                return "{\"success\": false, \"message\": \"Email already in use\"}";
            }

            // Create new user
            User user = new User();
            user.setEmail(userDTO.getEmail());
            user.setDisplayName(userDTO.getDisplayName());
            user.setPassword(userDTO.getPassword()); // In a real app, use password encoding
            user.setUid(java.util.UUID.randomUUID().toString()); // Generate a unique ID
            user.setEnabled(true);
            user.setOnline(true);
            user.setCreatedAt(Instant.now());
            user.setUpdatedAt(Instant.now());
            user.setProvider("local"); // Indicate this is a local account, not OAuth

            // Save the user
            User savedUser = userService.create(user);

            // Set user session attributes
            session.setAttribute("uid", savedUser.getUid());
            session.setAttribute("email", savedUser.getEmail());
            session.setAttribute("displayName", savedUser.getDisplayName());

            return "{\"success\": true, \"redirect\": \"/\"}";
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"An error occurred: " + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Get the user and update online status
            String uid = (String) session.getAttribute("uid");
            if (uid != null && userService.existsByUid(uid)) {
                User user = userService.findByUid(uid);
                user.setOnline(false);
                userService.create(user);
            }

            // Invalidate the session
            session.invalidate();
        }

        return "redirect:/auth/login?logout";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "auth/register";
    }
}