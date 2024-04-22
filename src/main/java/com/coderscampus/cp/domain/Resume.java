package com.coderscampus.cp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resumeFile; // TODO decide on how we are storing the resume file itself

    public Resume() {
    }

    public Resume(Long id, String resumeFile) {
        this.id = id;
        this.resumeFile = resumeFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(String resumeFile) {
        this.resumeFile = resumeFile;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", resumeFile='" + resumeFile + '\'' +
                '}';
    }
}
