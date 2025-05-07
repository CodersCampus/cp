package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Finalproject;
import com.coderscampus.cp.service.FinalprojectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/finalproject")
public class FinalprojectController {

    @Autowired
    private FinalprojectService finalprojectService;

    @GetMapping("")
    public String home(ModelMap model) {
        List<Finalproject> finalprojects = finalprojectService.findAll();
        model.put("finalprojects", finalprojects);
        model.addAttribute("pageTitle", "Finalproject Read");
        model.put("isFinalproject", true);
        return "finalproject/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        Finalproject finalproject = new Finalproject();
        model.put("finalproject", finalproject);
        model.addAttribute("pageTitle", "Finalproject Create");
        model.put("isFinalproject", true);
        return "finalproject/create";
    }

    @PostMapping("/create")
    public String create(Finalproject finalproject, @RequestParam("uid") String uid) {
        finalproject = finalprojectService.saveByUid(finalproject, uid);
        return "redirect:/finalproject";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        Finalproject finalproject = finalprojectService.findById(id);
        model.put("finalproject", finalproject);
        model.addAttribute("pageTitle", "Finalproject Update");
        model.put("isFinalproject", true);
        return "finalproject/update";
    }

    @PostMapping("/update")
    public String update(Finalproject finalproject) {
        finalprojectService.save(finalproject);
        return "redirect:/finalproject";
    }

    @PostMapping("/delete")
    public String delete(Finalproject finalproject) {
        finalprojectService.delete(finalproject);
        return "redirect:/finalproject";
    }
}
