package com.coderscampus.cp.manual;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.coderscampus")
public class TestDataSeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestDataSeederApplication.class, args);
    }
}
