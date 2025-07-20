package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Foobar;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.service.FoobarService;
import com.coderscampus.cp.service.SessionManagerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/foobar")
public class FoobarController {

    @Autowired
    private FoobarService foobarService;

    private final SessionManagerService sessionManagerService;

    public FoobarController(SessionManagerService sessionManagerService) {
        this.sessionManagerService = sessionManagerService;
    }

    @GetMapping("")
    public String home(ModelMap model, HttpSession httpSession) {
        // Authentication check
        if (!sessionManagerService.isAuthenticated(httpSession)) {
            return "redirect:/";
        }

        UserDTO currentUser = (UserDTO) httpSession.getAttribute("currentUser");
        System.out.println("CURRENT USER: " + currentUser);
        List<Foobar> foobars = foobarService.findAll();
        model.put("foobars", foobars);
        model.addAttribute("pageTitle", "Foobar");
        model.put("isFoobar", true);
        return "foobar/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        Foobar foobar = new Foobar();
        model.put("foobar", foobar);
        model.addAttribute("pageTitle", "Foobar");
        model.put("isFoobar", true);
        return "foobar/create";
    }

    @PostMapping("/create")
    public String create(Foobar foobar, @RequestParam("uid") String uid) {
        foobar = foobarService.saveByUid(foobar, uid);
        return "redirect:/foobar";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id, HttpSession httpSession) {
        // Authentication check
        if (!sessionManagerService.isAuthenticated(httpSession)) {
            return "redirect:/";
        }

        String uid = (String) httpSession.getAttribute("uid");
        Foobar foobar = foobarService.findById(id);

        if (foobar.getStudent() != null && foobar.getStudent().getUid().equals(uid)) {
            model.put("foobar", foobar);
            model.addAttribute("pageTitle", "Foobar Update");
            model.put("isFoobar", true);
            return "foobar/update";
        } else {
            return "redirect:/foobar";
        }
    }

    @PostMapping("/update")
    public String update(Foobar foobar, HttpSession httpSession) {
        // Authentication check
        if (!sessionManagerService.isAuthenticated(httpSession)) {
            return "redirect:/";
        }

        String uid = (String) httpSession.getAttribute("uid");
        foobarService.saveByUid(foobar, uid);
        return "redirect:/foobar";
    }

    @PostMapping("/delete")
    public String delete(Foobar foobar) {
        foobarService.delete(foobar);
        return "redirect:/foobar";
    }
}
