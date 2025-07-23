package com.coderscampus.cp.dto;

import com.coderscampus.cp.domain.Student;

import java.time.Instant;

public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private Integer assignmentNum;
    private String ide;
    private final Instant dateCreated;
    private Boolean willingToMentor;
    private String mentee;

    public StudentDTO(Student foundStudent) {
        this.id = foundStudent.getId();
        this.name = foundStudent.getName();
        this.email = foundStudent.getEmail();
        this.assignmentNum = foundStudent.getAssignmentNum();
        this.ide = foundStudent.getIde();
        this.dateCreated = foundStudent.getDateCreated();
        this.mentee = foundStudent.getMentee();
        this.willingToMentor = foundStudent.getWillingToMentor();
    }

    public StudentDTO() {
        this(new Student());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAssignmentNum() {
        return assignmentNum;
    }

    public void setAssignmentNum(Integer assignmentNum) {
        this.assignmentNum = assignmentNum;
    }

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public Boolean getWillingToMentor() {
        return willingToMentor;
    }

    public void setWillingToMentor(Boolean willingToMentor) {
        this.willingToMentor = willingToMentor;
    }

    public String getMentee() {
        return mentee;
    }

    public void setMentee(String mentee) {
        this.mentee = mentee;
    }

    @Override
    public String toString() {
        return "StudentDTO [id=" + id + ", name=" + name + ", email=" + email
                + ", assignmentNum=" + assignmentNum + ", ide=" + ide
                + ", dateCreated=" + dateCreated + ", willingToMentor="
                + willingToMentor + ", mentee=" + mentee + "]";
    }

}
