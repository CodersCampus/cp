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
    private String handle;
    private String enhancedReadMe;     // convert to boolean
    private String renamedAssignments; // convert to boolean
    private String pinnedRepos;        // convert to boolean
    private String externalLinks;      // convert to list
    private String image;
    private String headline;
    private String contactDetails;
    private URL url;

    public GitHub() {

    }

    public GitHub(String handle, String enhancedReadMe, String renamedAssignments, String pinnedRepos, String externalLinks, String image, String headline, String contactDetails, URL url) {
        this.handle = handle;
        this.enhancedReadMe = enhancedReadMe;
        this.renamedAssignments = renamedAssignments;
        this.pinnedRepos = pinnedRepos;
        this.externalLinks = externalLinks;
        this.image = image;
        this.headline = headline;
        this.contactDetails = contactDetails;
        this.url = url;
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

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getEnhancedReadMe() {
        return enhancedReadMe;
    }

    public void setEnhancedReadMe(String enhancedReadMe) {
        this.enhancedReadMe = enhancedReadMe;
    }

    public String getRenamedAssignments() {
        return renamedAssignments;
    }

    public void setRenamedAssignments(String renamedAssignments) {
        this.renamedAssignments = renamedAssignments;
    }

    public String getPinnedRepos() {
        return pinnedRepos;
    }

    public void setPinnedRepos(String pinnedRepos) {
        this.pinnedRepos = pinnedRepos;
    }

    public String getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(String externalLinks) {
        this.externalLinks = externalLinks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GitHub{" +
                "id=" + id +
                ", student=" + student +
                ", handle='" + handle + '\'' +
                ", enhancedReadMe='" + enhancedReadMe + '\'' +
                ", renamedAssignments='" + renamedAssignments + '\'' +
                ", pinnedRepos='" + pinnedRepos + '\'' +
                ", externalLinks='" + externalLinks + '\'' +
                ", image='" + image + '\'' +
                ", headline='" + headline + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}


