package com.coderscampus.cp.dto;

import java.util.List;

import com.coderscampus.cp.domain.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStatusDTO {
	@JsonProperty("email")
	private String email;
	@JsonProperty("assignmentSubmitted")
	private String assignmentSubmitted;
	@JsonProperty("assignmentExpected")
	private String assignmentExpected;
	@JsonProperty("weeksInBootcamp")
	private String weeksInBootcamp;
	@JsonProperty("status")
	private String status;
	@JsonProperty("roles")
	private List<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssignmentSubmitted() {
        return assignmentSubmitted;
    }

    public void setAssignmentSubmitted(String assignmentSubmitted) {
        this.assignmentSubmitted = assignmentSubmitted;
    }

    public String getAssignmentExpected() {
        return assignmentExpected;
    }

    public void setAssignmentExpected(String assignmentExpected) {
        this.assignmentExpected = assignmentExpected;
    }

    public String getWeeksInBootcamp() {
        return weeksInBootcamp;
    }

    public void setWeeksInBootcamp(String weeksInBootcamp) {
        this.weeksInBootcamp = weeksInBootcamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserStatusDTO [" +
                "email=" + email +
                ", assignmentSubmitted=" + assignmentSubmitted +
                ", assignmentExpected=" + assignmentExpected +
                ", weeksInBootcamp=" + weeksInBootcamp +
                ", status=" + status +
                ", roles=" + roles +
                "]";
    }
}
