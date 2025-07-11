package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
    private final UserService userService;

    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String dashboardView(ModelMap model, HttpSession httpSession) {
        boolean authenticated = isAuthenticated(httpSession, model);

        if (!authenticated) {
            return "dashboard/index";
        }

        UserDTO user = (UserDTO) httpSession.getAttribute("currentUser");
        System.out.println("User authenticated: " + user.getDisplayName());
        return "dashboard/index"; // Return the dashboard view
    }

    @PostMapping("/send-oauth")
    @ResponseBody
    public String getOauth(@RequestBody AuthObjectDTO authDto, HttpSession httpSession) {
        if (authDto == null) {
            return "FAILURE";
        }

        // Check if a user exists with the uid
        User user = userService.findByUid(authDto.getUid());

        // If no user is found, create a new User
        if (user == null) {
            user = new User();
            user.setUid(authDto.getUid());
            user.setEmail(authDto.getEmail());
            user.setDisplayName(authDto.getDisplayName());
            user.setPhotoUrl(authDto.getPhotoUrl());
            user.setEnabled(true);
            user.setOnline(true);
            user.setRole(User.Role.ROLE_STUDENT);
        }

        // Update user properties
        user.setEmail(authDto.getEmail());
        user.setDisplayName(authDto.getDisplayName());
        user.setPhotoUrl(authDto.getPhotoUrl());
        user.setEnabled(true);
        user.setOnline(true);
        user.setRole(User.Role.ROLE_STUDENT);

        // Save the user
        user = userService.save(user);

        // Get UserDTO from the created or updated User
        UserDTO userDTO = new UserDTO(user);

        // Store in session
        httpSession.setAttribute("uid", authDto.getUid());
        httpSession.setAttribute("currentUser", userDTO);

        System.out.println("User authenticated and stored in session: " + userDTO.getDisplayName());
        return "SUCCESS";
    }

    public static boolean isAuthenticated(HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        UserDTO user = (UserDTO) httpSession.getAttribute("currentUser");
        return uid != null && user != null;
    }

    /**
     * Checks if the user is authenticated and adds authentication information to the model.
     *
     * @param httpSession The HTTP session
     * @param model       The model to add authentication information to (optional)
     * @return true if authenticated, false otherwise
     */
    public static boolean isAuthenticated(HttpSession httpSession, ModelMap model) {
        String uid = (String) httpSession.getAttribute("uid");
        UserDTO user = (UserDTO) httpSession.getAttribute("currentUser");
        boolean authenticated = uid != null && user != null;

        if (model != null) {
            model.put("isAuthenticated", authenticated);
            if (authenticated) {
                model.put("user", user);
            }
        }

        return authenticated;
    }

}
