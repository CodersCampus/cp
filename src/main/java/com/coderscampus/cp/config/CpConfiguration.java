package com.coderscampus.cp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CpConfiguration {
	@Value ("${api-assignemts-url.base}")
	private String baseUrl;

    @Bean
    public String baseUrl() {
        return baseUrl;
    }
}
