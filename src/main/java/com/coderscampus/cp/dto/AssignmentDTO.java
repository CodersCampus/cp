package com.coderscampus.cp.dto;

import java.util.List;

import com.coderscampus.cp.domain.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentDTO {
	
	@JsonProperty("assignment")
	private String assignment;
	@JsonProperty("roles")
	private List<Role> roles;
	

}
