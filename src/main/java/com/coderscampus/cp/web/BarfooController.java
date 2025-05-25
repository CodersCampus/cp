package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Barfoo;
import com.coderscampus.cp.service.BarfooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/barfoo")
public class BarfooController {

    @Autowired
    private BarfooService barfooService;

    @GetMapping("")
    public String home(ModelMap model) {
        List<Barfoo> barfoos = barfooService.findAll();
        model.put("barfoos", barfoos);
        model.addAttribute("pageTitle", "Barfoo Read");
        model.put("isBarfoo", true);
        return "barfoo/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        Barfoo barfoo = new Barfoo();
        model.put("barfoo", barfoo);
        model.addAttribute("pageTitle", "Barfoo Create");
        model.put("isBarfoo", true);
        return "barfoo/create";
    }

    @PostMapping("/create")
    public String create(Barfoo barfoo, @RequestParam("uid") String uid) {
        barfoo = barfooService.saveByUid(barfoo, uid);
        return "redirect:/barfoo";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        Barfoo barfoo = barfooService.findById(id);
        model.put("barfoo", barfoo);
        model.addAttribute("pageTitle", "Barfoo Update");
        model.put("isBarfoo", true);
        return "barfoo/update";
    }

    @PostMapping("/update")
    public String update(Barfoo barfoo) {
        barfooService.save(barfoo);
        return "redirect:/barfoo";
    }

    @PostMapping("/delete")
    public String delete(Barfoo barfoo) {
        barfooService.delete(barfoo);
        return "redirect:/barfoo";
    }
}
