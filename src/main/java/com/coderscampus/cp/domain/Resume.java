package com.coderscampus.cp.domain;

import jakarta.persistence.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Resume {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private String phoneNumber;
    private String email;
    private String city;
    private String state;
    private URL linkedIn;
    private URL gitHub;
    private String summary;
    private String skills;
    private String workExperience;
    private String education;
    private String projects;

    public Resume() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public URL getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(URL linkedIn) {
        this.linkedIn = linkedIn;
    }

    public URL getGitHub() {
        return gitHub;
    }

    public void setGitHub(URL gitHub) {
        this.gitHub = gitHub;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", linkedIn=" + linkedIn +
                ", gitHub=" + gitHub +
                ", summary='" + summary + '\'' +
                ", skills='" + skills + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", education='" + education + '\'' +
                ", projects='" + projects + '\'' +
                '}';
    }
}
