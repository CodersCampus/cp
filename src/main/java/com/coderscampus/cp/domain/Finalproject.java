package com.coderscampus.cp.domain;

import jakarta.persistence.*;

@Entity
public class Finalproject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid;
    private String projectName;
    private String proposal; // A brief description of the project proposal - Link to a document or a string description
    private String crud;
    private String tables;
    private String views;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Finalproject() {
    }

    public Finalproject(Long id, String projectName, String proposal, String crud, String tables, String views, Student student) {
        this.id = id;
        this.projectName = projectName;
        this.proposal = proposal;
        this.crud = crud;
        this.tables = tables;
        this.views = views;
        this.student = student;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public String getCrud() {
        return crud;
    }

    public void setCrud(String crud) {
        this.crud = crud;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Finalproject{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", projectName='" + projectName + '\'' +
                ", proposal='" + proposal + '\'' +
                ", crud='" + crud + '\'' +
                ", tables='" + tables + '\'' +
                ", views='" + views + '\'' +
                ", student=" + student +
                '}';
    }
}