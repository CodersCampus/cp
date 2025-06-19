package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Networkingperson;
import com.coderscampus.cp.service.NetworkingpersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/networkingperson")
public class NetworkingpersonController {

    @Autowired
    private NetworkingpersonService networkingpersonService;

    @GetMapping("")
    public String home(ModelMap model) {
        List<Networkingperson> networkingpersons = networkingpersonService.findAll();
        model.put("networkingpersons", networkingpersons);
        model.addAttribute("pageTitle", "Networking People");
        model.put("isNetworkingperson", true);
        return "networkingperson/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        Networkingperson networkingperson = new Networkingperson();
        model.put("networkingperson", networkingperson);
        model.addAttribute("pageTitle", "Networking People");
        model.put("isNetworkingperson", true);
        return "networkingperson/create";
    }

    @PostMapping("/create")
    public String create(Networkingperson networkingperson, @RequestParam("uid") String uid) {
        networkingperson = networkingpersonService.saveByUid(networkingperson, uid);
        return "redirect:/networkingperson";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        Networkingperson networkingperson = networkingpersonService.findById(id);

        if (networkingperson.getStudent() != null && networkingperson.getStudent().getUid().equals(uid)) {
            model.put("networkingperson", networkingperson);
            model.addAttribute("pageTitle", "Networking People");
            model.put("isNetworkingperson", true);
            return "networkingperson/update";
        } else {
            return "redirect:/networkingperson";
        }
    }

    @PostMapping("/update")
    public String update(Networkingperson networkingperson, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        networkingpersonService.saveByUid(networkingperson, uid);
        return "redirect:/networkingperson";
    }

    @PostMapping("/delete")
    public String delete(Networkingperson networkingperson) {
        networkingpersonService.delete(networkingperson);
        return "redirect:/networkingperson";
    }
}
