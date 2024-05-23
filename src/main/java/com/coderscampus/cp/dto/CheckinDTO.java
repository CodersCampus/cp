package com.coderscampus.cp.dto;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;

import java.time.Instant;

public class CheckinDTO {

    private Long id;
    private Instant date;
    private Integer nextAssignment;
    private Boolean blockers;
    private String blockerDescription;
    private Checkin.Role role;
    private Checkin.CodingType codingType;

    public CheckinDTO(Checkin checkin) {
        super();
        this.id = checkin.getId();
        this.date = checkin.getDate();
        this.nextAssignment = checkin.getNextAssignment();
        this.blockers = checkin.getBlockers();
        this.blockerDescription = checkin.getBlockerDescription();
        this.role = checkin.getRole();
        this.codingType = checkin.getCodingType();
    }
    public CheckinDTO() {
        this(new Checkin());
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

    public Boolean getBlockers() {
        return blockers;
    }

    public void setBlockers(Boolean blockers) {
        this.blockers = blockers;
    }

    public String getBlockerDescription() {
        return blockerDescription;
    }

    public void setBlockerDescription(String blockerDescription) {
        this.blockerDescription = blockerDescription;
    }

    public Checkin.Role getRole() {
        return role;
    }

    public void setRole(Checkin.Role role) {
        this.role = role;
    }

    public Checkin.CodingType getCodingType() {
        return codingType;
    }

    public void setCodingType(Checkin.CodingType codingType) {
        this.codingType = codingType;
    }

    @Override
    public String toString() {
        return "CheckinDTO{" +
                "id=" + id +
                ", date=" + date +
                ", nextAssignment=" + nextAssignment +
                ", blockers=" + blockers +
                ", blockerDescription='" + blockerDescription + '\'' +
                ", role=" + role +
                ", codingType=" + codingType +
                '}';
    }
}
