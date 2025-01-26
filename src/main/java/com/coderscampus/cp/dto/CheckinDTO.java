package com.coderscampus.cp.dto;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.util.InstantFormatter;

import java.time.Instant;
import java.time.LocalDate;

public class CheckinDTO {

    private Long id;
    private Instant date;
    private String createdAt;
    private Integer nextAssignment;
    private Boolean blocker;
    private String blockerDescription;
    private Long studentId;
    private Boolean setUp;
    private Boolean available;
    private String name;
    private Integer issueNumber;

    public CheckinDTO() {
        super();
    }

    public CheckinDTO(long studentId) {
        super();
        this.studentId = studentId;
    }

    public CheckinDTO(String name, Integer issueNumber, Instant date) {
        this.name = name;
        this.issueNumber = issueNumber;
        this.date = date;
    }
    public CheckinDTO(Checkin checkin) {
        super();
        this.name=checkin.getStudent().getName();
        this.id = checkin.getId();
        this.date = checkin.getDate();
        this.createdAt = InstantFormatter.format(date);
        this.nextAssignment = checkin.getNextAssignment();
        this.blocker = checkin.getBlocker();
        this.blockerDescription = checkin.getBlockerDescription();
        this.setUp = checkin.getSetup();
        this.available = checkin.getAvailable();
        this.studentId = checkin.getStudent().getId();
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Boolean getSetUp() {
        return setUp;
    }

    public void setSetUp(Boolean setUp) {
        this.setUp = setUp;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Integer getNextAssignment() {
        return nextAssignment;
    }

    public void setNextAssignment(Integer nextAssignment) {
        this.nextAssignment = nextAssignment;
    }

    public Boolean getBlocker() {
        return blocker;
    }

    public void setBlocker(Boolean blocker) {
        this.blocker = blocker;
    }

    public String getBlockerDescription() {
        return blockerDescription;
    }

    public void setBlockerDescription(String blockerDescription) {
        this.blockerDescription = blockerDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CheckinDTO{" +
                "id=" + id +
                ", date=" + date +
                ", createdAt='" + createdAt + '\'' +
                ", nextAssignment=" + nextAssignment +
                ", blocker=" + blocker +
                ", blockerDescription='" + blockerDescription + '\'' +
                ", studentId=" + studentId +
                ", setUp=" + setUp +
                ", available=" + available +
                ", name='" + name + '\'' +
                ", issueNumber=" + issueNumber +
                '}';
    }
}

