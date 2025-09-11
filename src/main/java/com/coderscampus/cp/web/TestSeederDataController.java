package com.coderscampus.cp.web;

import com.coderscampus.cp.manual.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSeederDataController {

    @Autowired
    private TestDataService testDataService;

    @GetMapping("/seed")
    public String seed () throws Exception {
        testDataService.prepData();
        return "seedDataRun";
    }

}
