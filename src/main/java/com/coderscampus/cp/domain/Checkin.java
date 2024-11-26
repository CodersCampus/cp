package com.coderscampus.cp.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String uid;
    private Boolean isSetup;
    private Boolean available;
    private final Instant date;
    private Integer nextAssignment;
    private Boolean blocker;
    private String blockerDescription;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ActivityLog> activityLogs = new ArrayList<>();

    public Checkin() {
        this.date = Instant.now();
    }

    public Checkin(String uid, List<ActivityLog> activityLogs, Integer nextAssignment, Boolean blocker, String blockerDescription, Student student) {
        this();
        this.uid = uid;
        this.activityLogs = activityLogs;
        this.nextAssignment = nextAssignment;
        this.blocker = blocker;
        this.blockerDescription = blockerDescription;
        this.student = student;

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

    public Boolean getBlocker() {
        return blocker;
    }

    public void setBlocker(Boolean blocker) {
        this.blocker = blocker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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


    public String getBlockerDescription() {
        return blockerDescription;
    }

    public void setBlockerDescription(String blockerDescription) {
        this.blockerDescription = blockerDescription;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public List<ActivityLog> getActivityLogs() {
        return activityLogs;
    }

    public void setActivityLogs(List<ActivityLog> activityLog) {
        this.activityLogs = activityLog;
    }


    @Override
    public String toString() {
        return "Checkin{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", isSetup=" + isSetup +
                ", available=" + available +
               
                ", nextAssignment=" + nextAssignment +
                ", blocker=" + blocker +
                ", blockerDescription='" + blockerDescription + '\'' +
                
              
                '}';
    }
}
