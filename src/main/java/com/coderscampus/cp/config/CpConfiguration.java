package com.coderscampus.cp.config;

import org.springframework.beans.factory.annotation.Value;

public class CpConfiguration {
	@Value ("${cc.assignment.cp.baseUrl}")
	private String baseUrl;

}
