package com.coderscampus.cp.domain;

import jakarta.persistence.*;

@Entity
public class Networkingperson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String linkedinUrl;
    private String techStack;
    private String firstContactDate;
    private String lastContactDate;
    private String otherNotesAboutPerson;
    private String uid;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Networkingperson() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Foobar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", student=" + student +
                '}';
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    public String getFirstContactDate() {
        return firstContactDate;
    }

    public void setFirstContactDate(String firstContactDate) {
        this.firstContactDate = firstContactDate;
    }

    public String getLastContactDate() {
        return lastContactDate;
    }

    public void setLastContactDate(String lastContactDate) {
        this.lastContactDate = lastContactDate;
    }

    public String getOtherNotesAboutPerson() {
        return otherNotesAboutPerson;
    }

    public void setOtherNotesAboutPerson(String otherNotesAboutPerson) {
        this.otherNotesAboutPerson = otherNotesAboutPerson;
    }
}
