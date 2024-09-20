package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.MyTableRowThing;
import com.coderscampus.cp.service.MyTableRowThingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/myTableRowThing")
public class MyTableRowThingController {

    private final MyTableRowThingService myTableRowThingService;

    MyTableRowThingController(MyTableRowThingService myTableRowThingService) {
        this.myTableRowThingService = myTableRowThingService;
    }

    @GetMapping("/")
    public String home(ModelMap model) {
        List<MyTableRowThing> myTableRowThings = myTableRowThingService.findAll();
        model.put("myTableRowThings", myTableRowThings);
        model.addAttribute("pageTitle", "MyTableRowThing Read");
        model.put("isMyTableRowThing", true);
        return "myTableRowThing/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        MyTableRowThing myTableRowThing = new MyTableRowThing();
        model.put("myTableRowThing", myTableRowThing);
        model.addAttribute("pageTitle", "MyTableRowThing Create");
        model.put("isMyTableRowThing", true);
        return "myTableRowThing/create";
    }

    @PostMapping("/create")
    public String create(MyTableRowThing myTableRowThing, @RequestParam("uid") String uid) {
        myTableRowThing = myTableRowThingService.save(myTableRowThing);
        return "redirect:/myTableRowThing/";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        MyTableRowThing myTableRowThing = myTableRowThingService.findById(id);
        model.put("myTableRowThing", myTableRowThing);
        model.addAttribute("pageTitle", "MyTableRowThing Update");
        model.put("isMyTableRowThing", true);
        return "myTableRowThing/update";
    }

    @PostMapping("/update")
    public String update(MyTableRowThing myTableRowThing) {
        myTableRowThingService.save(myTableRowThing);
        return "redirect:/myTableRowThing/";
    }

    @PostMapping("/delete")
    public String delete(MyTableRowThing myTableRowThing) {
        myTableRowThingService.delete(myTableRowThing);
        return "redirect:/myTableRowThing/";
    }
}
