package com.coderscampus.cp.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
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
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid;
    private Instant date;
    private Integer nextAssignment;
    private Boolean blockers;
    private String blockerDescription;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;
    private Role role;
    private CodingType codingType;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ActivityLog> activityLog = new ArrayList<>();

    public Checkin() {
        this.date = Instant.now();
    }

    public Checkin(Long id, String uid, List<ActivityLog> activityLog, Integer nextAssignment, Boolean blockers, String blockerDescription, Student student, Role role, CodingType codingType) {
        super();
        this.id = id;
        this.uid = uid;
        this.activityLog = activityLog;
        this.nextAssignment = nextAssignment;
        this.blockers = blockers;
        this.blockerDescription = blockerDescription;
        this.student = student;
        this.role = role;
        this.codingType = codingType;
    }

    public Checkin(CheckinDTO checkinDTO) {
        super();
        Long id = checkinDTO.getId();
        if (id != null && id > 0) {
            this.id = id;
        }
        // Left out activityLog
        this.nextAssignment = checkinDTO.getNextAssignment();
        this.blockers = checkinDTO.getBlockers();
        this.blockerDescription = checkinDTO.getBlockerDescription();
        //left out student
        this.role = checkinDTO.getRole();
        this.codingType = checkinDTO.getCodingType();
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
