package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Profile;
import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.service.ProfileService;
import com.coderscampus.cp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private static final List<String> MONTH_NAMES = List.of(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );
    private static final List<String> DEGREE_NAMES = List.of(
            "Bachelor of Arts", "Bachelor of Science", "Master of Arts", "Master of Science",
            "Doctor of Philosophy", "Associate Degree", "Certificate", "Diploma"
    );
    private static final List<Integer> YEARS = IntStream.rangeClosed(1960, 2025)
            .boxed()
            .toList();

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getProfile(ModelMap model, HttpSession session) {
        // Authentication check - properly handle null session attribute
        Object currentUserObj = session.getAttribute("currentUser");
        if (!(currentUserObj instanceof UserDTO currentUser)) {
            return "error/401"; // Redirect to the error page if not authenticated
        }

        List<String> months = new ArrayList<>(MONTH_NAMES);
        List<String> degrees = new ArrayList<>(DEGREE_NAMES);
        List<Integer> years = new ArrayList<>(YEARS);

        Profile profile = profileService.findById(currentUser.getId());

        model.put("months", months);
        model.put("degrees", degrees);
        model.put("years", years);
        model.put("profile", profile);

        return "profile/index";
    }

    @PostMapping("/edit")
    public String putProfile(Profile profile, HttpSession session) {
        // Get the current user from the session
        Object currentUserObj = session.getAttribute("currentUser");
        if (!(currentUserObj instanceof UserDTO currentUser)) {
            return "error/401"; // Redirect to the error page if not authenticated
        }

        // Get the actual User entity from the database
        User user = userService.findById(currentUser.getId());
        if (user == null) {
            return "redirect:/error/404"; // User not found
        }

        // Set the user for the profile
        profile.setUser(user);
        profile.setId(user.getId()); // Ensure ID consistency

        profileService.save(profile);
        return "redirect:/profile";
    }
}