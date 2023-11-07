package com.coderscampus.springwise.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    public Student() {

    }


    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", assignmentNum=" + assignmentNum + ", githubHandle="
                + githubHandle + ", ide=" + ide + ", uid=" + uid + "]";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Integer assignmentNum;
    private String githubHandle;
    private String ide;
    private String uid;
    private String linkedIn;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FinalProject> finalProject = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAssignmentNum() {
        return assignmentNum;
    }

    public void setAssignmentNum(Integer assignmentNum) {
        this.assignmentNum = assignmentNum;
    }

    public String getGithubHandle() {
        return githubHandle;
    }

    public void setGithubHandle(String githubHandle) {
        this.githubHandle = githubHandle;
    }

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public List<FinalProject> getFinalProject() {
        return finalProject;
    }

    public void setFinalProject(List<FinalProject> finalProject) {
        this.finalProject = finalProject;
    }
}
