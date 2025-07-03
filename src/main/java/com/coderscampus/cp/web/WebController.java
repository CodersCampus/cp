package com.coderscampus.cp.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Value("${show.database.console.link}")
    private boolean showDatabaseConsoleLink;

    @GetMapping("/dashboard")
    public String index() {
        return "dashboard/index"; // This will resolve to src/main/resources/templates/dashboard/index.html
    }

    @GetMapping("/support")
    public String support() {
        return "dashboard/support"; // This will resolve to src/main/resources/templates/dashboard/support.html
    }

    @GetMapping("/documentation")
    public String documentation() {
        return "dashboard/documentation"; // This will resolve to src/main/resources/templates/dashboard/documentation.html
    }
}
