package com.coderscampus.cp.domain;

import jakarta.persistence.*;

import java.net.URL;

@Entity
public class GitHub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private Boolean handle;
    private Boolean enhancedReadMe;     // convert to boolean
    private Boolean renamedAssignments; // convert to boolean
    private Boolean pinnedRepos;        // convert to boolean
    private String externalLinks;      // convert to list
    private Boolean image;
    private Boolean headline;
    private Boolean contactDetails;
    private String url;

    public GitHub() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getHandle() {
        return handle;
    }

    public void setHandle(Boolean handle) {
        this.handle = handle;
    }

    public Boolean getEnhancedReadMe() {
        return enhancedReadMe;
    }

    public void setEnhancedReadMe(Boolean enhancedReadMe) {
        this.enhancedReadMe = enhancedReadMe;
    }

    public Boolean getRenamedAssignments() {
        return renamedAssignments;
    }

    public void setRenamedAssignments(Boolean renamedAssignments) {
        this.renamedAssignments = renamedAssignments;
    }

    public Boolean getPinnedRepos() {
        return pinnedRepos;
    }

    public void setPinnedRepos(Boolean pinnedRepos) {
        this.pinnedRepos = pinnedRepos;
    }

    public String getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(String externalLinks) {
        this.externalLinks = externalLinks;
    }

    public Boolean getImage() {
        return image;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }

    public Boolean getHeadline() {
        return headline;
    }

    public void setHeadline(Boolean headline) {
        this.headline = headline;
    }

    public Boolean getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(Boolean contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}



