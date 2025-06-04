package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Networkingresources;
import com.coderscampus.cp.service.NetworkingresourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/networkingresources")
public class NetworkingresourcesController {

    @Autowired
    private NetworkingresourcesService networkingresourcesService;

    @GetMapping("")
    public String home(ModelMap model) {
        List<Networkingresources> networkingresources = networkingresourcesService.findAll();
        model.put("networkingresources", networkingresources);
        model.addAttribute("pageTitle", "Networking Resources");
        model.put("isNetworkingresources", true);
        return "networkingresources/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        Networkingresources networkingresources = new Networkingresources();
        model.put("networkingresources", networkingresources);
        model.addAttribute("pageTitle", "Networking Resources");
        model.put("isNetworkingresources", true);
        return "networkingresources/create";
    }

    @PostMapping("/create")
    public String create(Networkingresources networkingresources, @RequestParam("uid") String uid) {
        networkingresources = networkingresourcesService.saveByUid(networkingresources, uid);
        return "redirect:/networkingresources";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        Networkingresources networkingresources = networkingresourcesService.findById(id);
        model.put("networkingresources", networkingresources);
        model.addAttribute("pageTitle", "Networking Resources");
        model.put("isNetworkingresources", true);
        return "networkingresources/update";
    }

    @PostMapping("/update")
    public String update(Networkingresources networkingresources) {
        networkingresourcesService.save(networkingresources);
        return "redirect:/networkingresources";
    }

    @PostMapping("/delete")
    public String delete(Networkingresources networkingresources) {
        networkingresourcesService.delete(networkingresources);
        return "redirect:/networkingresources";
    }
}
