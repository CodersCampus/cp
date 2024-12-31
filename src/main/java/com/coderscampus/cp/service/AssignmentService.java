package com.coderscampus.cp.service;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coderscampus.cp.dto.AssignmentDTO;

@Service
public class AssignmentService {

	
	private final String baseUrl;
	
	private final RestTemplate rt;

	public AssignmentService(String baseUrl, RestTemplate rt) {
		super();
		this.baseUrl = baseUrl;
		this.rt = rt;
	}
	
	
	
	public ResponseEntity<AssignmentDTO> fetchAssignment(String email){
		
		
		URI uri= buildURI("assignment",email);
		
	}
	
	
}
