package com.coderscampus.cp.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public abstract class HasUidBase {
    // wants to be a valid UID but visually distinct and readable as pure fluff
    public static void String SOME_CONSTANT_TO_BE_DEFINED_CAREFULLY_THOUGHTFULLY="asdfasdfs"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String uid;
    protected Instant date;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    public void removeUid() {
        this.uid = SOME_CONSTANT_TO_BE_DEFINED_CAREFULLY_THOUGHTFULLY;
    }

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

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    p

    // Student
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public CodingType getCodingType() {
        return codingType;
    }

    public void setCodingType(CodingType codingType) {
        this.codingType = codingType;
    }

    public List<ActivityLog> getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(List<ActivityLog> activityLog) {
        this.activityLog = activityLog;
    }

    @Override
    public String toString() {
        return "Checkin{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", date=" + date +
                ", nextAssignment=" + nextAssignment +
                ", blockers=" + blockers +
                ", blockerDescription='" + blockerDescription + '\'' +
                ", student=" + student +
                ", activityLog=" + activityLog +
                '}';
    }

    // ENUMS
    public enum CodingType {
        FOUNDATIONS, CRUD, CODE_REVIEW, DESIGN, DOCUMENTATION
    }

    public enum Role {
        FOUNDATIONS, OBSERVER, CODER, GUIDE, SCRUM_MASTER, PRODUCT_OWNER
    }
}
