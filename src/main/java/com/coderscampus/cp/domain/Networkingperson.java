package com.coderscampus.cp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Networkingperson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String linkedinUrl;
    private String techStack;
    private String firstContactDate;
    private String lastContactDate; //this is so you can make sure you are keeping in touch
    private String otherNotesAboutPerson;
    
    public Networkingperson() {
    }

    public Networkingperson(Long id, String name, String linkedinUrl, String techStack, String firstContactDate, String lastContactDate, String otherNotesAboutPerson) {
        this.id = id;
        this.name = name;
        this.linkedinUrl = linkedinUrl;
        this.techStack = techStack;
        this.firstContactDate = firstContactDate;
        this.lastContactDate = lastContactDate;
        this.otherNotesAboutPerson = otherNotesAboutPerson;
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
