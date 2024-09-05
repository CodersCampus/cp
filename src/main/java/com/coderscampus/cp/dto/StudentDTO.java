package com.coderscampus.cp.dto;

import com.coderscampus.cp.domain.Student;

import java.time.Instant;

public class StudentDTO {

    private Long id;
    private String name;
    private Integer assignmentNum;
    private String ide;
    private Instant dateCreated;
    private Boolean willingToMentor;
    private String mentee;

    public StudentDTO(Student foundStudent) {
        this.id = foundStudent.getId();
        this.name = foundStudent.getName();
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

    public String getName() {
        return name;
    }

    public Integer getAssignmentNum() {
        return assignmentNum;
    }

    public String getIde() {
        return ide;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAssignmentNum(Integer assignmentNum) {
        this.assignmentNum = assignmentNum;
    }

    public void setIde(String ide) {
        this.ide = ide;
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
        return "StudentDTO [id=" + id + ", name=" + name + ", assignmentNum=" + assignmentNum + ", ide=" + ide
                + ", dateCreated=" + dateCreated + ", willingToMentor=" + willingToMentor + ", mentee=" + mentee + "]";
    }

}
