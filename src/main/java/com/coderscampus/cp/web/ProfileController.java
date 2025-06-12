package com.coderscampus.cp.web;

import com.coderscampus.cp.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String viewProfile(ModelMap model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        if (uid != null && !uid.isEmpty()) {
            UserStatusDTO userStatus = profileService.getUserStatus(uid);
            model.addAttribute("userStatus", userStatus);
            model.addAttribute("isStudent", true);
        }
        model.addAttribute("pageTitle", "Profile View");
        return "profile/view";
    }
}
