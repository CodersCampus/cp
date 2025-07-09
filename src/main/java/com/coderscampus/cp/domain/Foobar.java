package com.coderscampus.cp.domain;

import jakarta.persistence.*;

@Entity
public class Foobar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid;
    private Integer issueNumber;
    private String title;
    @Column(length = 5000, nullable = true)
    private String description;
    private int priority;
    private Boolean status;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String url;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Foobar() {
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

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Foobar [" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", issueNumber='" + issueNumber + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", student=" + student +
                ']';
    }

    public enum Type {
        BUG, FEATURE, TASK
    }
}
