package com.coderscampus.springwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.coderscampus.practice", "com.coderscampus.springwise"})

public class SpringWiseApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringWiseApplication.class, args);
	}
}