package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.service.LinkedInService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.coderscampus.cp.web.WebController.isAuthenticated;

@Controller
@RequestMapping("/linkedin")
public class LinkedInController {

    @Autowired
    private LinkedInService linkedInService;


    @GetMapping("")
    public String home(ModelMap model, HttpSession httpSession) {
        // Authentication check
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }

        List<LinkedIn> linkedIns = linkedInService.findAll();
        model.put("linkedIns", linkedIns);
        model.addAttribute("pageTitle", "LinkedIn Read");
        model.put("isLinkedIn", true);
        return "linkedin/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        LinkedIn linkedIn = new LinkedIn();
        model.put("linkedIn", linkedIn);
        model.addAttribute("pageTitle", "LinkedIn Create");
        model.put("isLinkedIn", true);
        return "linkedin/create";
    }

    @PostMapping("/create")
    public String create(LinkedIn linkedIn, @RequestParam("uid") String uid, ModelMap model) {

        if (!linkedInService.isValidURL(linkedIn.getUrl())) {
            model.put("linkedIn", linkedIn);
            model.put("error", "Invalid URL");
            return "linkedin/create";
            }
        linkedIn = linkedInService.saveByUid(linkedIn, uid);
        return "redirect:/linkedin";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        LinkedIn linkedIn = linkedInService.findById(id);

        if (linkedIn.getStudent() != null && linkedIn.getStudent().getUid().equals(uid)) {
            model.put("linkedIn", linkedIn);
            model.addAttribute("pageTitle", "LinkedIn Update");
            model.put("isLinkedIn", true);
            return "linkedin/update";
        } else {
            return "redirect:/linkedin";
        }
    }

    @PostMapping("/update")
    public String update(LinkedIn linkedIn, HttpSession httpSession, ModelMap model) {

        if (!linkedInService.isValidURL(linkedIn.getUrl())) {
            model.put("linkedIn", linkedIn);
            model.put("error", "Invalid URL");
            return "linkedin/update";
            }
        String uid = (String) httpSession.getAttribute("uid");
        linkedInService.saveByUid(linkedIn, uid);
        return "redirect:/linkedin";
    }

    @PostMapping("/delete")
    public String delete(LinkedIn linkedIn) {
        linkedInService.delete(linkedIn);
        return "redirect:/linkedin";
    }
}
