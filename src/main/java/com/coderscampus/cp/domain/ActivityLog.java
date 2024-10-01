package com.coderscampus.cp.domain;

import com.coderscampus.cp.domain.Checkin.CodingType;
import com.coderscampus.cp.domain.Checkin.Role;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isSetUp;
    private Boolean available;
    private Role role;
    private Instant startTime;
    private Instant endTime;
    private CodingType codingType;
    private Integer issueNumber;
    @Column(length = 5000, nullable = true)
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "checkin_id")
    private Checkin checkin;

    public ActivityLog() {
        this.startTime = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getSetUp() {
        return isSetUp;
    }

    public void setSetUp(Boolean setUp) {
        isSetUp = setUp;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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

    public CodingType getCodingType() {
        return codingType;
    }

    public void setCodingType(CodingType codingType) {
        this.codingType = codingType;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Checkin getCheckin() {
        return checkin;
    }

    public void setCheckin(Checkin checkin) {
        this.checkin = checkin;
    }

    @Override
    public String toString() {
        return "ActivityLog{" +
                "id=" + id +
                ", isSetUp=" + isSetUp +
                ", available=" + available +
                ", role=" + role +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", codingType=" + codingType +
                ", issueNumber=" + issueNumber +
                ", comment='" + comment + '\'' +
                ", checkin=" + checkin +
                '}';
    }
}