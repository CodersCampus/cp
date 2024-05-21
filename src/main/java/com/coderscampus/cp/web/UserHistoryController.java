package com.coderscampus.cp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.cp.domain.UserHistory;
import com.coderscampus.cp.service.UserHistoryService;

@Controller
@RequestMapping("/user-history")
public class UserHistoryController {

    @Autowired
    private UserHistoryService userHistoryService;

    @GetMapping("/")
    public String home(ModelMap model) {
        List<UserHistory> userHistorys = userHistoryService.findAll();
        model.put("userHistorys", userHistorys);
        model.addAttribute("pageTitle", "User History Read");
        model.put("isUserHistory", true);
        return "user-history/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        UserHistory userHistory = new UserHistory();
        model.put("userHistory", userHistory);
        model.addAttribute("pageTitle", "User History Create");
        model.put("isUserHistory", true);
        return "user-history/create";
    }

    @PostMapping("/create")
    public String create(UserHistory userHistory) {
        userHistoryService.save(userHistory);
        return "redirect:/user-history/";
    }


    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        UserHistory userHistory = userHistoryService.findById(id);
        model.put("userHistory", userHistory);
        model.addAttribute("pageTitle", "User History Update");
        model.put("isUserHistory", true);
        return "user-history/update";
    }

    @PostMapping("/update")
    public String update(UserHistory userHistory) {
        userHistoryService.save(userHistory);
        return "redirect:/user-history/";
    }

    @PostMapping("/delete")
    public String delete(UserHistory userHistory) {
        userHistoryService.delete(userHistory);
        return "redirect:/user-history/";
    }
}
