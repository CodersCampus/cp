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
    private Integer assignment;
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

	public Boolean getSetUp() {
		return isSetUp;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setSetUp(Boolean setUp) {
		isSetUp = setUp;
	}

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
    	CODER, DRIVER, SCRUM_MASTER, PO, OBSERVER, NULL
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
		return "Checkin{" +
				"id=" + id +
				", uid='" + uid + '\'' +
				", date=" + date +
				", assignment=" + assignment +
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
