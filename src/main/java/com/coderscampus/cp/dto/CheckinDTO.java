package com.coderscampus.cp.dto;

import com.coderscampus.cp.domain.Checkin;

import java.time.Instant;

public class CheckinDTO {

    private Long id;
    private Instant date;
    private Integer nextAssignment;
    private Boolean blocker;
    private String blockerDescription;
    private Long studentId;
    private Boolean isSetup;
    private Boolean available;

    public CheckinDTO(Checkin checkin) {
        super();
        this.id = checkin.getId();
        this.date = checkin.getDate();
        this.nextAssignment = checkin.getNextAssignment();
        this.blocker = checkin.getBlocker();
        this.blockerDescription = checkin.getBlockerDescription();
        this.isSetup = checkin.getSetup();
        this.available = checkin.getAvailable();
        this.studentId = checkin.getStudent().getId();
    }

    public Boolean getSetup() {
        return isSetup;
    }

    public void setSetup(Boolean setup) {
        isSetup = setup;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public CheckinDTO(long studentId) {
        super();
        this.studentId = studentId;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public CheckinDTO() {
        super();
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


    @Override
    public String toString() {
        return "CheckinDTO{" +
                "id=" + id +
                ", date=" + date +
                ", nextAssignment=" + nextAssignment +
                ", blocker=" + blocker +
                ", blockerDescription='" + blockerDescription + '\'' +
                ", studentId=" + studentId +
                ", isSetup=" + isSetup +
                ", available=" + available +
                '}';
    }
}
