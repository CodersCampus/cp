package com.coderscampus.springwise.domain;

import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Boolean blockers;
    private Integer assignment;
    private Boolean isSetUp;
    private Boolean available;
    private Role role;
    private Instant startTime;
    private Instant endTime;
    private String uid;
    private Integer issueNumber;
    private CodingType codingType;
    private String blockerDescription;
    
    
    
    public Boolean getIsSetUp() {
		return isSetUp;
	}
	public void setIsSetUp(Boolean isSetUp) {
		this.isSetUp = isSetUp;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Instant getStartTime() {
		return startTime;
	}
	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}
	public Instant getEndTime() {
		return endTime;
	}
	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(Integer issueNumber) {
		this.issueNumber = issueNumber;
	}
	public CodingType getCodingType() {
		return codingType;
	}
	public void setCodingType(CodingType codingType) {
		this.codingType = codingType;
	}
	public String getBlockerDescription() {
		return blockerDescription;
	}
	public void setBlockerDescription(String blockerDescription) {
		this.blockerDescription = blockerDescription;
	}
	public enum CodingType{
    	CRUD, CODE_REVIEW, DESIGN, DOCUMENTATION, NULL
    }
    
    public enum Role{
    	CODER, DRIVER, SCRUM_MASTER, PM, OBSERVER, NULL
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Boolean getBlockers() {
        return blockers;
    }
    public void setBlockers(Boolean blockers) {
        this.blockers = blockers;
    }
    public Integer getAssignment() {
        return assignment;
    }
    public void setAssignment(Integer assignment) {
        this.assignment = assignment;
    }
  
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }
	@Override
	public String toString() {
		return "Checkin [id=" + id + ", date=" + date + ", blockers=" + blockers + ", assignment=" + assignment
				+ ", isSetUp=" + isSetUp + ", available=" + available + ", role=" + role + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", uid=" + uid + ", issueNumber=" + issueNumber + ", codingType="
				+ codingType + ", blockerDescription=" + blockerDescription + "]";
	}
   


}
