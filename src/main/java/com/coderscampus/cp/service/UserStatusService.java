package com.coderscampus.cp.service;

import java.net.URI;

import com.coderscampus.cp.dto.UserStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
        int assignmentSubmitted = Integer.parseInt(getUserStatus(email).getBody().getAssignmentSubmitted());
        return assignmentSubmitted >= MAX_ASSIGNMENTS ? assignmentSubmitted : assignmentSubmitted + 1;
    }

    public ResponseEntity<UserStatusDTO> getUserStatus(String email) {
        URI uri = buildURI(email);
        return rt.getForEntity(uri, UserStatusDTO.class);
    }

    public URI buildURI(String email) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment(email);
        // Do a try catch here
        return uri.build().toUri();
    }

}
