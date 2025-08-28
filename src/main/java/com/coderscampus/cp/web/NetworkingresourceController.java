package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Networkingresource;
import com.coderscampus.cp.service.NetworkingresourceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/networkingresource")
public class NetworkingresourceController {

    @Autowired
    private NetworkingresourceService networkingresourceService;

    @GetMapping("")
    public String home(ModelMap model, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        List<Networkingresource> networkingresource = networkingresourceService.findListByUid(uid);
        model.put("networkingresource", networkingresource);
        model.addAttribute("pageTitle", "Networking Resources");
        model.put("isNetworkingresource", true);
        return "networkingresource/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        Networkingresource networkingresource = new Networkingresource();
        model.put("networkingresource", networkingresource);
        model.addAttribute("pageTitle", "Networking Resources");
        model.put("isNetworkingresource", true);
        return "networkingresource/create";
    }

    @PostMapping("/create")
    public String create(Networkingresource networkingresource, @RequestParam("uid") String uid) {
        networkingresource = networkingresourceService.saveByUid(networkingresource, uid);
        return "redirect:/networkingresource";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        Networkingresource networkingresource = networkingresourceService.findById(id);

        if (networkingresource.getStudent() != null && networkingresource.getStudent().getUid().equals(uid)) {
            model.put("networkingresource", networkingresource);
            model.addAttribute("pageTitle", "Networking Resource Update");
            model.put("isNetworkingresource", true);
            return "networkingresource/update";
        } else {
            return "redirect:/networkingresource";
        }

    }

    @PostMapping("/update")
    public String update(Networkingresource networkingresource, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        networkingresourceService.saveByUid(networkingresource, uid);
        return "redirect:/networkingresource";
    }

    @PostMapping("/delete")
    public String delete(Networkingresource networkingresource) {
        networkingresourceService.delete(networkingresource);
        return "redirect:/networkingresource";
    }
}
