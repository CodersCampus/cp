package com.coderscampus.cp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStatus {
	@JsonProperty("id")
	private String id;
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	

}
