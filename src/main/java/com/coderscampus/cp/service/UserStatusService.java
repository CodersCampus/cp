package com.coderscampus.cp.service;

import com.coderscampus.cp.dto.UserStatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UserStatusService {

    private static final int MAX_ASSIGNMENTS = 15;
    private final String baseUrl;
    private final RestTemplate rt;

    public UserStatusService(String baseUrl) {
        this.baseUrl = baseUrl;
        this.rt = new RestTemplate();
    }

    public int getUserNextAssignment(String email) {
        UserStatusDTO status = getUserStatus(email).getBody();
        int assignmentSubmitted = 0;
        if (status != null) {
            assignmentSubmitted = Integer.parseInt(status.getAssignmentSubmitted());
        }
        return assignmentSubmitted >= MAX_ASSIGNMENTS ? assignmentSubmitted : assignmentSubmitted + 1;
    }

    public ResponseEntity<UserStatusDTO> getUserStatus(String email) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment(email).build().toUri();
        try {
            return rt.getForEntity(uri, UserStatusDTO.class);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
