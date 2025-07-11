package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Foobar;
import com.coderscampus.cp.service.FoobarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/foobar")
public class FoobarController {
    private final FoobarService foobarService;

    public FoobarController(FoobarService foobarService) {
        this.foobarService = foobarService;
    }

    @GetMapping("")
    public String home(ModelMap model) {
        List<Foobar> foobars = foobarService.findAll();
        model.put("foobars", foobars);
        setupCommonModelAttributes(model, "Foobar");
        return "foobar/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        Foobar foobar = createDefaultFoobar();
        model.put("foobar", foobar);
        setupFoobarFormModel(model, "Foobar");
        return "foobar/create";
    }

    @PostMapping("/create")
    public String create(Foobar foobar, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        foobar = foobarService.saveByUid(foobar, uid);
        return "redirect:/foobar/update/" + foobar.getId();
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        Foobar foobar = foobarService.findById(id);
        model.put("foobar", foobar);
        setupFoobarFormModel(model, "Foobar Update");
        return "foobar/update";
    }

    @PostMapping("/update")
    public String update(Foobar foobar, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        foobarService.saveByUid(foobar, uid);
        return "redirect:/foobar";
    }

    @PostMapping("/delete")
    public String delete(Foobar foobar) {
        foobarService.delete(foobar);
        return "redirect:/foobar";
    }

    private void setupCommonModelAttributes(ModelMap model, String pageTitle) {
        model.addAttribute("pageTitle", pageTitle);
        model.put("isFoobar", true);
    }

    private void setupFoobarFormModel(ModelMap model, String pageTitle) {
        model.addAttribute("typeList", Foobar.Type.values());
        setupCommonModelAttributes(model, pageTitle);
    }

    private Foobar createDefaultFoobar() {
        Foobar foobar = new Foobar();
        foobar.setStatus(false);
        foobar.setPriority(0);
        return foobar;
    }
}
