package com.coderscampus.springwise.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class Checkin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uid;
	private LocalDateTime date;
	private Integer nextAssignment;
	private Boolean blockers;
	private String blockerDescription;
	private Boolean isSetUp;
	private Boolean available;
	private Role role;
	private Instant startTime;
	private Instant endTime;
	private CodingType codingType;
	private Integer issueNumber;
	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;

	// ID
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	// UID
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	// Date
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	// Assignment
	public Integer getNextAssignment() {
		return nextAssignment;
	}
	public void setNextAssignment(Integer nextAssignment) {
		this.nextAssignment = nextAssignment;
	}

	// Blockers
	public Boolean getBlockers() {
		return blockers;
	}
	public void setBlockers(Boolean blockers) {
		this.blockers = blockers;
	}

	// Blocker Description
	public String getBlockerDescription() {
		return blockerDescription;
	}
	public void setBlockerDescription(String blockerDescription) {
		this.blockerDescription = blockerDescription;
	}

	// Is Set Up
	public Boolean getIsSetUp() {
		return isSetUp;
	}
	public void setIsSetUp(Boolean isSetUp) {
		this.isSetUp = isSetUp;
	}

	// Available
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	// Role
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	// Start Time
	public Instant getStartTime() {
		return startTime;
	}
	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	// End Time
	public Instant getEndTime() {
		return endTime;
	}
	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	// Coding Type
	public CodingType getCodingType() {
		return codingType;
	}
	public void setCodingType(CodingType codingType) {
		this.codingType = codingType;
	}

	// Issue Number
	public Integer getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(Integer issueNumber) {
		this.issueNumber = issueNumber;
	}

	// Student
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	// ENUMS
	public enum CodingType{
		CRUD, CODE_REVIEW, DESIGN, DOCUMENTATION, NULL
	}
	public enum Role{
		CODER, DRIVER, SCRUM_MASTER, PO, OBSERVER, NULL
	}

	@Override
	public String toString() {
		return "Checkin{" +
				"id=" + id +
				", uid='" + uid + '\'' +
				", date=" + date +
				", assignment=" + nextAssignment +
				", blockers=" + blockers +
				", blockerDescription='" + blockerDescription + '\'' +
				", isSetUp=" + isSetUp +
				", available=" + available +
				", role=" + role +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", issueNumber=" + issueNumber +
				", codingType=" + codingType +
				", student=" + student +
				'}';
	}
}
